package com.example.TaskManager.service;

import com.example.TaskManager.entity.User;

import java.util.Optional;

public interface UserService {
    User register(String username, String password);
    Optional<User> login(String username, String password);
    User getUserById(Long id);
    User updateUser(User user);
    void deleteUserById(Long id);
}
