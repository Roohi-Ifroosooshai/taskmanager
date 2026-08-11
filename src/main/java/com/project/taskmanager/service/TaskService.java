package com.project.taskmanager.service;

import com.project.taskmanager.dto.TaskRequest;
import com.project.taskmanager.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(TaskRequest taskRequest);

    Task updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);
}