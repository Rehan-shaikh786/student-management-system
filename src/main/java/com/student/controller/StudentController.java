package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.entity.Student;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

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