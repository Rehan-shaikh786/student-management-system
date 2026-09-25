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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@Tag(
        name = "Enrollment Management",
        description = "APIs for managing student course enrollments"
)
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

    @Operation(
            summary = "Add a new enrollment",
            description = "Enrolls a student into a course."
    )
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

    @Operation(
            summary = "Get all enrollments",
            description = "Returns all student course enrollments."
    )
    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollmentResponses()
        );
    }

    @Operation(
            summary = "Get enrollment by ID",
            description = "Returns an enrollment using the specified enrollment ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentResponseById(id)
        );
    }

    @Operation(
            summary = "Delete an enrollment",
            description = "Deletes an existing enrollment using the enrollment ID."
    )
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