package edu.icet.service;

import edu.icet.dto.Course;

import java.util.List;

public interface CourseService {
    Boolean createCourse(Course course);

    List<Course> getCourses();

    Course getCourseById(int id);

    List<Course> getCoursesByName(String name);

    List<Course> getCoursesByTeacherId(int id);

    List<Course> getAllCourses();

    Boolean updateCourse(Course course);

    Boolean deleteCourse(int id);
}
