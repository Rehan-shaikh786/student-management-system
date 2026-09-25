package com.student.dao;

import com.student.entity.Enrollment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EnrollmentDaoImpl implements EnrollmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addEnrollment(Enrollment enrollment) {
        entityManager.persist(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        return entityManager.find(Enrollment.class, id);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return entityManager
                .createQuery(
                        "SELECT e FROM Enrollment e",
                        Enrollment.class
                )
                .getResultList();
    }

    @Override
    public void deleteEnrollment(int id) {

        Enrollment enrollment =
                entityManager.find(Enrollment.class, id);

        if (enrollment != null) {
            entityManager.remove(enrollment);
        }
    }

    @Override
    public Enrollment getEnrollmentByStudentAndCourse(
            int studentId,
            int courseId) {

        List<Enrollment> enrollments = entityManager
                .createQuery(
                        "SELECT e FROM Enrollment e " +
                                "WHERE e.student.id = :studentId " +
                                "AND e.course.id = :courseId",
                        Enrollment.class
                )
                .setParameter("studentId", studentId)
                .setParameter("courseId", courseId)
                .getResultList();

        return enrollments.isEmpty()
                ? null
                : enrollments.get(0);
    }
}