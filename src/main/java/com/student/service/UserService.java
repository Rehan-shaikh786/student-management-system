package com.student.service;

import com.student.entity.User;

public interface UserService {

    void registerUser(User user);

    User getUserByEmail(String email);

    boolean verifyPassword(String rawPassword, String encodedPassword);
}