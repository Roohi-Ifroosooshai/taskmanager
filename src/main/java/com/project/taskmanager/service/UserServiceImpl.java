package com.project.taskmanager.service;

import com.project.taskmanager.dto.UserRequest;
import com.project.taskmanager.entity.User;
import com.project.taskmanager.exception.TaskNotFoundException;
import com.project.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "User not found with id: " + id
                        ));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}