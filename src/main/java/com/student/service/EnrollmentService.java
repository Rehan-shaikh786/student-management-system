package com.student.service;

import com.student.dto.EnrollmentResponse;
import com.student.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    void addEnrollment(Enrollment enrollment);

    Enrollment getEnrollmentById(int id);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(int id);

    EnrollmentResponse getEnrollmentResponseById(int id);

    List<EnrollmentResponse> getAllEnrollmentResponses();
}