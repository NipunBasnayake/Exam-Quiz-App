package edu.icet.service.impl;

import edu.icet.dto.Exam;
import edu.icet.service.ExamService;

import java.util.List;

public class ExamServiceImpl implements ExamService {
    @Override
    public void createExam(Exam exam) {

    }

    @Override
    public Exam getExamByCourseId(Integer id) {
        return null;
    }

    @Override
    public List<Exam> getAll(Integer id) {
        return List.of();
    }

    @Override
    public void updateExam(Exam exam) {

    }

    @Override
    public void deleteExamById(Integer id) {

    }

    @Override
    public void deleteExamByCourseId(Integer id) {

    }
}
