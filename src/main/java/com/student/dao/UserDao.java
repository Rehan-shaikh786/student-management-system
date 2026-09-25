package com.student.dao;

import com.student.entity.User;

public interface UserDao {

    void saveUser(User user);

    User getUserByEmail(String email);
}