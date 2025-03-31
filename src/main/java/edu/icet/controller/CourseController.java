package edu.icet.controller;

import edu.icet.dto.Course;
import edu.icet.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
@RequiredArgsConstructor
public class CourseController {

    final CourseService courseService;

    @PostMapping("/create-course")
    public ResponseEntity<Boolean> createCourse(@RequestBody Course course) {
        if (course == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean created = courseService.createCourse(course);
        return created ?
                ResponseEntity.status(HttpStatus.CREATED).body(true) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Course course = courseService.getCourseById(id);
        return course != null ?
                ResponseEntity.ok(course) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> getCoursesByName(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Course> courses = courseService.getCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<List<Course>> getCoursesByTeacherId(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Course> courses = courseService.getCoursesByTeacherId(id);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateCourse(@RequestBody Course course) {
        if (course == null || course.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean updated = courseService.updateCourse(course);
        return updated ?
                ResponseEntity.ok(true) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        Boolean deleted = courseService.deleteCourse(id);
        return deleted ?
                ResponseEntity.ok(true) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}