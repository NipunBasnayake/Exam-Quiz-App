package edu.icet.service.impl;

import edu.icet.dto.Exam;
import edu.icet.entity.ExamEntity;
import edu.icet.repository.ExamRepository;
import edu.icet.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    final ExamRepository examRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createExam(Exam exam) {
        ExamEntity savedEntity = examRepository.save(modelMapper.map(exam, ExamEntity.class));
        return savedEntity.getId() != null;
    }

    @Override
    public List<Exam> getExamByCourseId(Integer id) {
        List<ExamEntity> byCourseId = examRepository.findByCourseId(id);
        List<Exam> exams = new ArrayList<>();
        byCourseId.forEach(examEntity -> exams.add(modelMapper.map(examEntity, Exam.class)));
        return exams;
    }

    @Override
    public List<Exam> getAll() {
        List<ExamEntity> all = examRepository.findAll();
        List<Exam> exams = new ArrayList<>();
        all.forEach(examEntity -> exams.add(modelMapper.map(examEntity, Exam.class)));
        return exams;
    }

    @Override
    public Boolean updateExam(Exam exam) {
        ExamEntity savedEntity = examRepository.save(modelMapper.map(exam, ExamEntity.class));
        return savedEntity.getId().equals(exam.getId());
    }

    @Override
    public Boolean deleteExamById(Integer id) {
        examRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteExamByCourseId(Integer id) {
        examRepository.deleteByCourseId(id);
        return true;
    }
}
