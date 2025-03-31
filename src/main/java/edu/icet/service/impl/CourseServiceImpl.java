package edu.icet.service.impl;

import edu.icet.dto.Course;
import edu.icet.entity.CourseEntity;
import edu.icet.repository.CourseRepository;
import edu.icet.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createCourse(Course course) {
        if (course == null) {
            log.error("Cannot create null course");
            throw new IllegalArgumentException("Course cannot be null");
        }

        try {
            CourseEntity courseEntity = modelMapper.map(course, CourseEntity.class);

            courseEntity.setCreatedAt(LocalDateTime.now());
            courseEntity.setUpdatedAt(LocalDateTime.now());

            CourseEntity savedEntity = courseRepository.save(courseEntity);
            return savedEntity.getId() != null;
        } catch (Exception e) {
            log.error("Error creating course: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Course> getCourses() {
        try {
            List<CourseEntity> courseEntities = courseRepository.findAll();
            List<Course> courses = new ArrayList<>();
            courseEntities.forEach(entity -> courses.add(modelMapper.map(entity, Course.class)));
            return courses;
        } catch (Exception e) {
            log.error("Error retrieving courses: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Course getCourseById(int id) {
        if (id <= 0) {
            log.error("Invalid course ID: {}", id);
            throw new IllegalArgumentException("Course ID must be a positive integer");
        }

        try {
            Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
            return optionalCourse.map(entity -> modelMapper.map(entity, Course.class)).orElse(null);
        } catch (Exception e) {
            log.error("Error retrieving course with ID {}: {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Course> getCoursesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            log.error("Invalid course name: {}", name);
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }

        try {
            List<CourseEntity> courseEntities = courseRepository.findByNameContainingIgnoreCase(name);
            List<Course> courses = new ArrayList<>();
            courseEntities.forEach(entity -> courses.add(modelMapper.map(entity, Course.class)));
            return courses;
        } catch (Exception e) {
            log.error("Error retrieving courses with name {}: {}", name, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> getCoursesByTeacherId(int id) {
        if (id <= 0) {
            log.error("Invalid teacher ID: {}", id);
            throw new IllegalArgumentException("Teacher ID must be a positive integer");
        }

        try {
            List<CourseEntity> courseEntities = courseRepository.findByTeacherId(id);
            List<Course> courses = new ArrayList<>();
            courseEntities.forEach(entity -> courses.add(modelMapper.map(entity, Course.class)));
            return courses;
        } catch (Exception e) {
            log.error("Error retrieving courses for teacher ID {}: {}", id, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            List<CourseEntity> courseEntities = courseRepository.findAll();
            List<Course> courses = new ArrayList<>();
            courseEntities.forEach(entity -> courses.add(modelMapper.map(entity, Course.class)));
            return courses;
        } catch (Exception e) {
            log.error("Error retrieving all courses: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean updateCourse(Course course) {
        if (course == null) {
            log.error("Cannot update null course");
            throw new IllegalArgumentException("Course cannot be null");
        }

        if (course.getId() == null || course.getId() <= 0) {
            log.error("Invalid course ID for update: {}", course.getId());
            throw new IllegalArgumentException("Course ID must be a positive integer");
        }

        try {
            if (!courseRepository.existsById(course.getId())) {
                log.warn("Cannot update course with ID {} - not found", course.getId());
                return false;
            }

            CourseEntity courseEntity = modelMapper.map(course, CourseEntity.class);
            courseEntity.setUpdatedAt(LocalDateTime.now());

            Optional<CourseEntity> existingCourse = courseRepository.findById(course.getId());
            existingCourse.ifPresent(existing -> courseEntity.setCreatedAt(existing.getCreatedAt()));

            CourseEntity savedEntity = courseRepository.save(courseEntity);
            return Objects.equals(savedEntity.getId(), course.getId());
        } catch (Exception e) {
            log.error("Error updating course with ID {}: {}", course.getId(), e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean deleteCourse(int id) {
        if (id <= 0) {
            log.error("Invalid course ID for deletion: {}", id);
            throw new IllegalArgumentException("Course ID must be a positive integer");
        }

        try {
            if (!courseRepository.existsById(id)) {
                log.warn("Cannot delete course with ID {} - not found", id);
                return false;
            }

            courseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting course with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }
}