package edu.icet.controller;

import edu.icet.dto.Exam;
import edu.icet.service.ExamService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Boolean> createExam(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.createExam(exam));
    }

    @GetMapping("/getExamByCourseId/{id}")
    public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable Integer id){
        return ResponseEntity.ok(examService.getExamByCourseId(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Exam>> getAll(){
        return ResponseEntity.ok(examService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateExam(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.updateExam(exam));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Boolean> deleteExamById(@PathVariable Integer id){
        return ResponseEntity.ok(examService.deleteExamById(id));
    }

    @DeleteMapping("/deleteByCourseId/{courseId}")
    public ResponseEntity<Boolean> deleteExamByCourseId(@PathVariable Integer courseId){
        return ResponseEntity.ok(examService.deleteExamByCourseId(courseId));
    }

}
