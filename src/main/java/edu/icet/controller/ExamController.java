package edu.icet.controller;

import edu.icet.dto.Exam;
import edu.icet.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@CrossOrigin
@RequiredArgsConstructor
public class ExamController {

    final ExamService examService;

    @PostMapping("/create-exam")
    public ResponseEntity<Boolean> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.createExam(exam));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable Integer id) {
        return ResponseEntity.ok(examService.getExamByCourseId(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateExam(@RequestBody Exam exam) {
        Boolean updated = examService.updateExam(exam);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExamById(@PathVariable Integer id) {
        Boolean deleted = examService.deleteExamById(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok(true);
    }
}