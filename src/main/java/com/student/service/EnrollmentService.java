package com.student.service;

import com.student.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    // Create
    void addEnrollment(Enrollment enrollment);

    // Read
    Enrollment getEnrollmentById(int id);

    List<Enrollment> getAllEnrollments();

    // Delete
    void deleteEnrollment(int id);
}