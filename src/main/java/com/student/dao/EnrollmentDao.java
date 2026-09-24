package com.student.dao;

import com.student.entity.Enrollment;

import java.util.List;

public interface EnrollmentDao {

    // Create
    void addEnrollment(Enrollment enrollment);

    // Read
    Enrollment getEnrollmentById(int id);

    List<Enrollment> getAllEnrollments();

    // Delete
    void deleteEnrollment(int id);
}