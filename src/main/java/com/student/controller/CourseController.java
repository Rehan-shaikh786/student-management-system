package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.entity.Course;
import com.student.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(
        name = "Course Management",
        description = "APIs for managing course records"
)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Get all courses",
            description = "Returns a list of all courses."
    )
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(
                courseService.getAllCourses()
        );
    }

    @Operation(
            summary = "Get course by ID",
            description = "Returns a course using the specified course ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    @Operation(
            summary = "Add a new course",
            description = "Creates a new course record."
    )
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

    @Operation(
            summary = "Update a course",
            description = "Updates an existing course using the course ID."
    )
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

    @Operation(
            summary = "Delete a course",
            description = "Deletes an existing course using the course ID."
    )
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