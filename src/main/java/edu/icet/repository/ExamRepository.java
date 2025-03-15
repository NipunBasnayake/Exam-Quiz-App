package edu.icet.repository;

import edu.icet.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
    List<ExamEntity> findByCourseId(Integer courseId);

    void deleteByCourseId(Integer id);
}
