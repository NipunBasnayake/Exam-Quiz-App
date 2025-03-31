package edu.icet.repository;

import edu.icet.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findByTeacherId(int id);

    List<CourseEntity> findByNameContainingIgnoreCase(String name);
}
