package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.dto.EnrollmentRequest;
import com.student.dto.EnrollmentResponse;
import com.student.entity.Course;
import com.student.entity.Enrollment;
import com.student.entity.Student;
import com.student.service.CourseService;
import com.student.service.EnrollmentService;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentController(
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService) {

        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addEnrollment(
            @Valid @RequestBody EnrollmentRequest request) {

        Student student =
                studentService.getStudentById(request.getStudentId());

        Course course =
                courseService.getCourseById(request.getCourseId());

        Enrollment enrollment =
                new Enrollment(student, course);

        enrollmentService.addEnrollment(enrollment);

        ApiResponse response = new ApiResponse(
                201,
                "Enrollment added successfully."
        );

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollmentResponses()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentResponseById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEnrollment(
            @PathVariable int id) {

        enrollmentService.deleteEnrollment(id);

        ApiResponse response = new ApiResponse(
                200,
                "Enrollment deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}