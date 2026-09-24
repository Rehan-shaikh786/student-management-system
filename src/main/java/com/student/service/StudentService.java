package com.student.service;

import com.student.entity.Student;

import java.util.List;

public interface StudentService {

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