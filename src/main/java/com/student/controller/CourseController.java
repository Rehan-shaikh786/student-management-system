package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.entity.Course;
import com.student.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(
                courseService.getAllCourses()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCourse(
            @Valid @RequestBody Course course) {

        courseService.addCourse(course);

        ApiResponse response = new ApiResponse(
                201,
                "Course added successfully."
        );

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCourse(
            @PathVariable int id,
            @Valid @RequestBody Course course) {

        course.setId(id);

        courseService.updateCourse(course);

        ApiResponse response = new ApiResponse(
                200,
                "Course updated successfully."
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(
            @PathVariable int id) {

        courseService.deleteCourse(id);

        ApiResponse response = new ApiResponse(
                200,
                "Course deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}