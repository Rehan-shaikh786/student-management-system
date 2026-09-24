package com.student.dao;

import com.student.entity.Course;
import com.student.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public void addCourse(Course course) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(course);

            transaction.commit();

            System.out.println("Course added successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Course getCourseById(int id) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session.get(Course.class, id);

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Course> getAllCourses() {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session
                    .createQuery("FROM Course", Course.class)
                    .list();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateCourse(Course course) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.update(course);

            transaction.commit();

            System.out.println("Course updated successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteCourse(int id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            Course course = session.get(Course.class, id);

            if (course != null) {

                session.delete(course);

                System.out.println("Course deleted successfully.");

            } else {

                System.out.println("Course not found.");
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
}