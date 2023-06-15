package com.example.task.service;

import com.example.task.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User newPropertiesForExistingUser(User user, Long id, String firsName, String lastName, int age, String email);

    void deleteUser(Long id);
}
