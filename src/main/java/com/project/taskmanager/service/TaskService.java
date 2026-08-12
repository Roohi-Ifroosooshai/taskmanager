package com.project.taskmanager.service;

import com.project.taskmanager.dto.TaskRequest;
import com.project.taskmanager.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Page<Task> getTasks(
            int page,
            int size,
            String sortBy,
            String direction);

    Task getTaskById(Long id);

    Task createTask(TaskRequest request);

    Task updateTask(Long id, TaskRequest request);

    void deleteTask(Long id);
}