package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.entity.Student;
import com.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(
        name = "Student Management",
        description = "APIs for managing student records"
)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Get all students",
            description = "Returns a list of all students."
    )
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    @Operation(
            summary = "Get student by ID",
            description = "Returns a student using the specified student ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    @Operation(
            summary = "Add a new student",
            description = "Creates a new student record."
    )
    @PostMapping
    public ResponseEntity<ApiResponse> addStudent(
            @Valid @RequestBody Student student) {

        studentService.addStudent(student);

        ApiResponse response = new ApiResponse(
                201,
                "Student added successfully."
        );

        return ResponseEntity
                .status(201)
                .body(response);
    }

    @Operation(
            summary = "Update a student",
            description = "Updates an existing student using the student ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        student.setId(id);

        studentService.updateStudent(student);

        ApiResponse response = new ApiResponse(
                200,
                "Student updated successfully."
        );

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a student",
            description = "Deletes an existing student using the student ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(
            @PathVariable int id) {

        studentService.deleteStudent(id);

        ApiResponse response = new ApiResponse(
                200,
                "Student deleted successfully."
        );

        return ResponseEntity.ok(response);
    }
}