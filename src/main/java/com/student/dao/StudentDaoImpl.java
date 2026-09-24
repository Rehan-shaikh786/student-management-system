package com.student.dao;

import com.student.entity.Student;
import com.student.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void addStudent(Student student) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(student);

            transaction.commit();

            System.out.println("Student added successfully.");

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
    public Student getStudentById(int id) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session.get(Student.class, id);

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
    public List<Student> getAllStudents() {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session
                    .createQuery("FROM Student", Student.class)
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
    public void updateStudent(Student student) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.update(student);

            transaction.commit();

            System.out.println("Student updated successfully.");

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
    public void deleteStudent(int id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {
                session.delete(student);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
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