package com.student.dao;

import com.student.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByEmail(String email) {

        try {
            return entityManager
                    .createQuery(
                            "SELECT u FROM User u WHERE u.email = :email",
                            User.class
                    )
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}