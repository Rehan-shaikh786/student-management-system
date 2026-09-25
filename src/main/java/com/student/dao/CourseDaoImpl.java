package com.student.dao;

import com.student.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager
                .createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(int id) {

        Course course = entityManager.find(Course.class, id);

        if (course != null) {
            entityManager.remove(course);
        }
    }

    @Override
    public Course getCourseByCode(String courseCode) {

        List<Course> courses = entityManager
                .createQuery(
                        "SELECT c FROM Course c WHERE c.courseCode = :courseCode",
                        Course.class
                )
                .setParameter("courseCode", courseCode)
                .getResultList();

        return courses.isEmpty() ? null : courses.get(0);
    }
}