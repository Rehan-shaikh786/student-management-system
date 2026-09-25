package com.student.dao;

import com.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student getStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudent(int id) {

        Student student = entityManager.find(Student.class, id);

        if (student != null) {
            entityManager.remove(student);
        }
    }

    @Override
    public Student getStudentByEmail(String email) {

        List<Student> students = entityManager
                .createQuery(
                        "SELECT s FROM Student s WHERE s.email = :email",
                        Student.class
                )
                .setParameter("email", email)
                .getResultList();

        return students.isEmpty() ? null : students.get(0);
    }
}