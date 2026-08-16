package com.project.taskmanager.controller;

import com.project.taskmanager.dto.UserRequest;
import com.project.taskmanager.entity.User;
import com.project.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(
            @Valid @RequestBody UserRequest request) {

        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        return userService.getUserById(id);
    }
}