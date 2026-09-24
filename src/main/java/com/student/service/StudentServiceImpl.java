package com.student.service;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.entity.Student;
import com.student.exception.StudentNotFoundException;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void addStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (student.getName() == null ||
                student.getName().trim().isEmpty()) {

            throw new IllegalArgumentException("Student name is required.");
        }

        if (student.getEmail() == null ||
                student.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException("Student email is required.");
        }

        if (!student.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0.");
        }

        if (student.getPhone() == null ||
                student.getPhone().trim().isEmpty()) {

            throw new IllegalArgumentException("Phone number is required.");
        }

        if (student.getCity() == null ||
                student.getCity().trim().isEmpty()) {

            throw new IllegalArgumentException("City is required.");
        }

        studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        Student student = studentDao.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with ID " + id + " not found."
            );
        }

        return student;
    }

    @Override
    public List<Student> getAllStudents() {

        return studentDao.getAllStudents();
    }

    @Override
    public void updateStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (student.getId() <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        if (student.getName() == null ||
                student.getName().trim().isEmpty()) {

            throw new IllegalArgumentException("Student name is required.");
        }

        if (student.getEmail() == null ||
                student.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException("Student email is required.");
        }

        if (!student.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0.");
        }

        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        Student student = studentDao.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with ID " + id + " not found."
            );
        }

        studentDao.deleteStudent(id);
    }
}