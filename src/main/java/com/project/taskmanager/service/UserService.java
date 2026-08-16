package com.project.taskmanager.service;

import com.project.taskmanager.dto.UserRequest;
import com.project.taskmanager.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserRequest request);

    User getUserById(Long id);

    List<User> getAllUsers();
}