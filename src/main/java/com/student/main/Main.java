package com.student.main;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.entity.Student;

public class Main {

    public static void main(String[] args) {

        Student student = new Student(
                "Rahul Patil",
                "rahul@gmail.com",
                "9876543210",
                22,
                "Male",
                "Patan",
                "Patan, Maharashtra"
        );

        StudentDao studentDao = new StudentDaoImpl();

        studentDao.addStudent(student);

        System.out.println("Student ID: " + student.getId());
    }
}