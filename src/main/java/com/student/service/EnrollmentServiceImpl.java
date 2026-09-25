package com.student.service;

import com.student.dao.EnrollmentDao;
import com.student.dto.EnrollmentResponse;
import com.student.entity.Enrollment;
import com.student.exception.DuplicateResourceException;
import com.student.exception.EnrollmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDao enrollmentDao;

    public EnrollmentServiceImpl(EnrollmentDao enrollmentDao) {
        this.enrollmentDao = enrollmentDao;
    }

    @Override
    public void addEnrollment(Enrollment enrollment) {

        if (enrollment == null) {
            throw new IllegalArgumentException(
                    "Enrollment cannot be null."
            );
        }

        if (enrollment.getStudent() == null) {
            throw new IllegalArgumentException(
                    "Student is required."
            );
        }

        if (enrollment.getCourse() == null) {
            throw new IllegalArgumentException(
                    "Course is required."
            );
        }

        int studentId = enrollment.getStudent().getId();
        int courseId = enrollment.getCourse().getId();

        Enrollment existingEnrollment =
                enrollmentDao.getEnrollmentByStudentAndCourse(
                        studentId,
                        courseId
                );

        if (existingEnrollment != null) {
            throw new DuplicateResourceException(
                    "Student is already enrolled in this course."
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
                    "Enrollment with ID " +
                            id +
                            " not found."
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
                    "Enrollment with ID " +
                            id +
                            " not found."
            );
        }

        enrollmentDao.deleteEnrollment(id);
    }

    @Override
    public EnrollmentResponse getEnrollmentResponseById(int id) {

        Enrollment enrollment = getEnrollmentById(id);

        return convertToResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollmentResponses() {

        return enrollmentDao.getAllEnrollments()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse convertToResponse(
            Enrollment enrollment) {

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getStudent().getName(),
                enrollment.getStudent().getEmail(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getCourseName(),
                enrollment.getCourse().getCourseCode()
        );
    }
}