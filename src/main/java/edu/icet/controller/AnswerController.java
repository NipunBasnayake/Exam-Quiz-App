package edu.icet.controller;

import edu.icet.dto.Answer;
import edu.icet.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin
@RequiredArgsConstructor
public class AnswerController {

    final AnswerService answerService;

    @PostMapping("/create-answer")
    public ResponseEntity<Boolean> createAnswer(@RequestBody Answer answer) {
        if (answer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean created = answerService.createAnswer(answer);
        return created ?
                ResponseEntity.status(HttpStatus.CREATED).body(true) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Answer answer = answerService.getAnswerById(id);
        return answer != null ?
                ResponseEntity.ok(answer) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/paper/{id}")
    public ResponseEntity<List<Answer>> getAnswersByPaperId(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Answer> answers = answerService.getAnswersByPaperId(id);
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Answer> getAnswerByQuestionId(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Answer answer = answerService.getAnswerByQuestionId(id);
        return answer != null ?
                ResponseEntity.ok(answer) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAll();
        return ResponseEntity.ok(answers);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAnswer(@RequestBody Answer answer) {
        if (answer == null || answer.getId() == null || answer.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean updated = answerService.updateAnswer(answer);
        return updated ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAnswerById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean deleted = answerService.deleteAnswerById(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}