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
        return ResponseEntity.ok(answerService.createAnswer(answer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswerById(@PathVariable Integer id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/paper/{id}")
    public ResponseEntity<List<Answer>> getAnswersByPaperId(@PathVariable Integer id) {
        return ResponseEntity.ok(answerService.getAnswersByPaperId(id));
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getAnswerByQuestionId(@PathVariable Integer id) {
        Answer answer = answerService.getAnswerByQuestionId(id);
        if (answer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAnswer(@RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.updateAnswer(answer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAnswerById(@PathVariable Integer id) {
        return ResponseEntity.ok(answerService.deleteAnswerById(id));
    }
}