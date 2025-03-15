package edu.icet.service;

import edu.icet.dto.Exam;

import java.util.List;

public interface ExamService {
    void createExam(Exam exam);

    Exam getExamByCourseId(Integer id);

    List<Exam> getAll(Integer id);

    void updateExam(Exam exam);

    void deleteExamById(Integer id);

    void deleteExamByCourseId(Integer id);
}
