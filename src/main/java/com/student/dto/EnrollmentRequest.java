package com.student.dto;

import jakarta.validation.constraints.Min;

public class EnrollmentRequest {

    @Min(value = 1, message = "Student ID must be greater than 0")
    private int studentId;

    @Min(value = 1, message = "Course ID must be greater than 0")
    private int courseId;

    public EnrollmentRequest() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}