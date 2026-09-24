package com.student.dao;

import com.student.entity.Enrollment;
import com.student.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EnrollmentDaoImpl implements EnrollmentDao {

    @Override
    public void addEnrollment(Enrollment enrollment) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            session.save(enrollment);

            transaction.commit();

            System.out.println("Enrollment added successfully.");

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
    public Enrollment getEnrollmentById(int id) {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session.get(Enrollment.class, id);

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
    public List<Enrollment> getAllEnrollments() {

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            return session
                    .createQuery("FROM Enrollment", Enrollment.class)
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
    public void deleteEnrollment(int id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            Enrollment enrollment =
                    session.get(Enrollment.class, id);

            if (enrollment != null) {

                session.delete(enrollment);

                System.out.println("Enrollment deleted successfully.");

            } else {

                System.out.println("Enrollment not found.");
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