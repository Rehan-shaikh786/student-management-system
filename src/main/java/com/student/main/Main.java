package com.student.main;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.entity.Student;

public class Main {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDaoImpl();

        // Find existing student
        Student student = studentDao.getStudentById(2);

        if (student != null) {

            // Update student details
            student.setName("Rahul Pawar");
            student.setEmail("rahulpawar@gmail.com");
            student.setPhone("9876501234");
            student.setAge(23);
            student.setCity("Satara");
            student.setAddress("Satara, Maharashtra");

            // Save updated student
            studentDao.updateStudent(student);

            System.out.println("Updated Student:");
            System.out.println(student);

        } else {

            System.out.println("Student not found.");
        }
    }
}