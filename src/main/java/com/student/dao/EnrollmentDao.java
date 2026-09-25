package com.student.dao;

import com.student.entity.Enrollment;

import java.util.List;

public interface EnrollmentDao {

    void addEnrollment(Enrollment enrollment);

    Enrollment getEnrollmentById(int id);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(int id);

    Enrollment getEnrollmentByStudentAndCourse(int studentId, int courseId);
}