package edu.icet.ontroller;

import edu.icet.dto.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@CrossOrigin
@RequiredArgsConstructor
public class ExamController {

    @PostMapping("/create-exam")
    public void createExam(@RequestBody Exam exam){

    }

}
