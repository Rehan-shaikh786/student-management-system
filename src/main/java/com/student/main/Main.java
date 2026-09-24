package com.student.main;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;

public class Main {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDaoImpl();

        // Delete student with ID 2
        studentDao.deleteStudent(2);
    }
}