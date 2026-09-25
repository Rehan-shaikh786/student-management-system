package com.student.service;

import com.student.dao.UserDao;
import com.student.entity.User;
import com.student.exception.DuplicateResourceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void registerUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        User existingUser = userDao.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            throw new DuplicateResourceException(
                    "User with email " + user.getEmail() + " already exists."
            );
        }

        String encodedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        userDao.saveUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean verifyPassword(
            String rawPassword,
            String encodedPassword) {

        return passwordEncoder.matches(
                rawPassword,
                encodedPassword
        );
    }
}