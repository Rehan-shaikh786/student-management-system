package com.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStudentNotFound(
            StudentNotFoundException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("error", "Student Not Found");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCourseNotFound(
            CourseNotFoundException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("error", "Course Not Found");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEnrollmentNotFound(
            EnrollmentNotFoundException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("error", "Enrollment Not Found");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(
            DuplicateResourceException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 409);
        response.put("error", "Duplicate Resource");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException e) {

        List<String> messages = new ArrayList<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        messages.add(error.getDefaultMessage())
                );

        Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("error", "Validation Failed");
        response.put("messages", messages);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(
            Exception e) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}