package com.student.controller;

import com.student.dto.ApiResponse;
import com.student.entity.Student;
import com.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            description = "Returns all student records."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Students retrieved successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
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
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student found successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
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
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Student created successfully",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ApiResponse.class
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid student data"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Admin permission required"
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse> addStudent(
            @RequestBody Student student) {

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
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ApiResponse.class
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Admin permission required"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        studentService.updateStudent(id, student);

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
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student deleted successfully",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ApiResponse.class
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Admin permission required"
            )
    })
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