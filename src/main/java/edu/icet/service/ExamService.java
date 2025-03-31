package edu.icet.service;

import edu.icet.dto.Exam;

import java.util.List;

public interface ExamService {
    Boolean createExam(Exam exam);

    List<Exam> getExamByCourseId(Integer id);

    List<Exam> getAll();

    Boolean updateExam(Exam exam);

    Boolean deleteExamById(Integer id);
}
