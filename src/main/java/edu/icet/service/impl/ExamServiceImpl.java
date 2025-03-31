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
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    final ExamRepository examRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createExam(Exam exam) {
        if (exam == null) {
            log.error("Cannot create null exam");
            throw new IllegalArgumentException("Exam cannot be null");
        }

        try {
            ExamEntity savedEntity = examRepository.save(modelMapper.map(exam, ExamEntity.class));
            return savedEntity.getId() != null;
        } catch (Exception e) {
            log.error("Error creating exam: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Exam> getExamByCourseId(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid course ID: {}", id);
            throw new IllegalArgumentException("Course ID must be a positive integer");
        }

        try {
            List<ExamEntity> byCourseId = examRepository.findByCourseId(id);
            List<Exam> exams = new ArrayList<>();
            byCourseId.forEach(examEntity -> exams.add(modelMapper.map(examEntity, Exam.class)));
            return exams;
        } catch (Exception e) {
            log.error("Error retrieving exams for course ID {}: {}", id, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Exam> getAll() {
        try {
            List<ExamEntity> all = examRepository.findAll();
            List<Exam> exams = new ArrayList<>();
            all.forEach(examEntity -> exams.add(modelMapper.map(examEntity, Exam.class)));
            return exams;
        } catch (Exception e) {
            log.error("Error retrieving all exams: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean updateExam(Exam exam) {
        if (exam == null) {
            log.error("Cannot update null exam");
            throw new IllegalArgumentException("Exam cannot be null");
        }

        if (exam.getId() == null || exam.getId() <= 0) {
            log.error("Invalid exam ID for update: {}", exam.getId());
            throw new IllegalArgumentException("Exam ID must be a positive integer");
        }

        try {
            if (!examRepository.existsById(exam.getId())) {
                log.warn("Cannot update exam with ID {} - not found", exam.getId());
                return false;
            }

            ExamEntity savedEntity = examRepository.save(modelMapper.map(exam, ExamEntity.class));
            return Objects.equals(savedEntity.getId(), exam.getId());
        } catch (Exception e) {
            log.error("Error updating exam with ID {}: {}", exam.getId(), e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteExamById(Integer id) {
        if (id == null || id <= 0) {
            log.error("Invalid exam ID for deletion: {}", id);
            throw new IllegalArgumentException("Exam ID must be a positive integer");
        }

        try {
            if (!examRepository.existsById(id)) {
                log.warn("Cannot delete exam with ID {} - not found", id);
                return false;
            }

            examRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting exam with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }
}