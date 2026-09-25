package com.student.service;

import com.student.dao.EnrollmentDao;
import com.student.dao.EnrollmentDaoImpl;
import com.student.entity.Enrollment;
import com.student.exception.EnrollmentNotFoundException;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

    @Override
    public void addEnrollment(Enrollment enrollment) {

        if (enrollment == null) {
            throw new IllegalArgumentException(
                    "Enrollment cannot be null."
            );
        }

        if (enrollment.getStudent() == null) {
            throw new IllegalArgumentException(
                    "Student is required for enrollment."
            );
        }

        if (enrollment.getCourse() == null) {
            throw new IllegalArgumentException(
                    "Course is required for enrollment."
            );
        }

        if (enrollment.getStudent().getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid student."
            );
        }

        if (enrollment.getCourse().getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid course."
            );
        }

        enrollmentDao.addEnrollment(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid enrollment ID."
            );
        }

        Enrollment enrollment =
                enrollmentDao.getEnrollmentById(id);

        if (enrollment == null) {
            throw new EnrollmentNotFoundException(
                    "Enrollment with ID " + id + " not found."
            );
        }

        return enrollment;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.getAllEnrollments();
    }

    @Override
    public void deleteEnrollment(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid enrollment ID."
            );
        }

        Enrollment enrollment =
                enrollmentDao.getEnrollmentById(id);

        if (enrollment == null) {
            throw new EnrollmentNotFoundException(
                    "Enrollment with ID " + id + " not found."
            );
        }

        enrollmentDao.deleteEnrollment(id);
    }
}