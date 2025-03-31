package edu.icet.controller;

import edu.icet.dto.Paper;
import edu.icet.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/paper")
@CrossOrigin
@RequiredArgsConstructor
public class PaperController {

    final PaperService paperService;

    @PostMapping
    public ResponseEntity<Boolean> createPaper(@RequestBody Paper paper) {
        Boolean result = paperService.createPaper(paper);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paper> findPaperById(@PathVariable Integer id) {
        Paper paper = paperService.findPaperById(id);
        if (paper != null) {
            return ResponseEntity.ok(paper);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Boolean> updatePaper(@RequestBody Paper paper) {
        Boolean result = paperService.updatePaper(paper);
        if (result) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePaper(@PathVariable Integer id) {
        Boolean result = paperService.deletePaper(id);
        if (result) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}