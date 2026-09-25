package com.student.main;

import com.student.service.CourseService;
import com.student.service.CourseServiceImpl;
import com.student.service.EnrollmentService;
import com.student.service.EnrollmentServiceImpl;

public class Main {

    public static void main(String[] args) {

        CourseService courseService = new CourseServiceImpl();
        EnrollmentService enrollmentService =
                new EnrollmentServiceImpl();

        // Test CourseNotFoundException
        try {

            courseService.getCourseById(999);

        } catch (Exception e) {

            System.out.println(
                    "Course Exception: " + e.getMessage()
            );
        }

        // Test EnrollmentNotFoundException
        try {

            enrollmentService.getEnrollmentById(999);

        } catch (Exception e) {

            System.out.println(
                    "Enrollment Exception: " + e.getMessage()
            );
        }
    }
}