package com.student.service;

import com.student.dao.StudentDao;
import com.student.entity.Student;
import com.student.exception.DuplicateResourceException;
import com.student.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null."
            );
        }

        Student existingStudent =
                studentDao.getStudentByEmail(student.getEmail());

        if (existingStudent != null) {
            throw new DuplicateResourceException(
                    "Student with email " +
                            student.getEmail() +
                            " already exists."
            );
        }

        studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid student ID."
            );
        }

        Student student =
                studentDao.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with ID " +
                            id +
                            " not found."
            );
        }

        return student;
    }

    @Override
    public List<Student> getAllStudents() {

        return studentDao.getAllStudents();
    }

    @Override
    public void updateStudent(int id, Student student) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null."
            );
        }

        if (student.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid student ID."
            );
        }

        Student existingStudent =
                studentDao.getStudentById(student.getId());

        if (existingStudent == null) {
            throw new StudentNotFoundException(
                    "Student with ID " +
                            student.getId() +
                            " not found."
            );
        }

        Student studentWithSameEmail =
                studentDao.getStudentByEmail(student.getEmail());

        if (studentWithSameEmail != null &&
                studentWithSameEmail.getId() != student.getId()) {

            throw new DuplicateResourceException(
                    "Student with email " +
                            student.getEmail() +
                            " already exists."
            );
        }

        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid student ID."
            );
        }

        Student student =
                studentDao.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with ID " +
                            id +
                            " not found."
            );
        }

        studentDao.deleteStudent(id);
    }
}