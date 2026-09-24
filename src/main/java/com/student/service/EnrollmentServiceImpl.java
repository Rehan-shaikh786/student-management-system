package com.student.service;

import com.student.dao.EnrollmentDao;
import com.student.dao.EnrollmentDaoImpl;
import com.student.entity.Enrollment;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

    @Override
    public void addEnrollment(Enrollment enrollment) {

        if (enrollment == null) {
            System.out.println("Enrollment cannot be null.");
            return;
        }

        if (enrollment.getStudent() == null) {
            System.out.println("Student is required for enrollment.");
            return;
        }

        if (enrollment.getCourse() == null) {
            System.out.println("Course is required for enrollment.");
            return;
        }

        if (enrollment.getStudent().getId() <= 0) {
            System.out.println("Invalid student.");
            return;
        }

        if (enrollment.getCourse().getId() <= 0) {
            System.out.println("Invalid course.");
            return;
        }

        enrollmentDao.addEnrollment(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(int id) {

        if (id <= 0) {
            System.out.println("Invalid enrollment ID.");
            return null;
        }

        return enrollmentDao.getEnrollmentById(id);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.getAllEnrollments();
    }

    @Override
    public void deleteEnrollment(int id) {

        if (id <= 0) {
            System.out.println("Invalid enrollment ID.");
            return;
        }

        enrollmentDao.deleteEnrollment(id);
    }
}