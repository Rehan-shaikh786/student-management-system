package com.student.main;

import com.student.entity.Course;
import com.student.entity.Enrollment;
import com.student.entity.Student;
import com.student.service.CourseService;
import com.student.service.CourseServiceImpl;
import com.student.service.EnrollmentService;
import com.student.service.EnrollmentServiceImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        EnrollmentService enrollmentService = new EnrollmentServiceImpl();


        // ==========================================
        // GET STUDENT
        // ==========================================

        Student student = studentService.getStudentById(1);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student:");
        System.out.println(student);


        // ==========================================
        // GET COURSE
        // ==========================================

        Course course = courseService.getCourseById(1);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("\nCourse:");
        System.out.println(course);


        // ==========================================
        // CREATE ENROLLMENT
        // ==========================================

        Enrollment enrollment = new Enrollment(student, course);

        enrollmentService.addEnrollment(enrollment);

        System.out.println("\nEnrollment ID: " + enrollment.getId());


        // ==========================================
        // GET ENROLLMENT BY ID
        // ==========================================

        System.out.println("\n----- Enrollment By ID -----");

        Enrollment foundEnrollment =
                enrollmentService.getEnrollmentById(enrollment.getId());

        if (foundEnrollment != null) {
            System.out.println(foundEnrollment);
        } else {
            System.out.println("Enrollment not found.");
        }


        // ==========================================
        // GET ALL ENROLLMENTS
        // ==========================================

        System.out.println("\n----- All Enrollments -----");

        List<Enrollment> enrollments =
                enrollmentService.getAllEnrollments();

        if (enrollments != null && !enrollments.isEmpty()) {

            for (Enrollment e : enrollments) {
                System.out.println(e);
            }

        } else {
            System.out.println("No enrollments found.");
        }
    }
}