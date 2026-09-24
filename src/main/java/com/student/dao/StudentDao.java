package com.student.dao;

import com.student.entity.Student;

import java.util.List;

public interface StudentDao {

    // Create
    void addStudent(Student student);

    // Read
    Student getStudentById(int id);

    List<Student> getAllStudents();

    // Update
    void updateStudent(Student student);

    // Delete
    void deleteStudent(int id);
}