package com.project.taskmanager.service;

import com.project.taskmanager.entity.Task;
import com.project.taskmanager.exception.TaskNotFoundException;
import com.project.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));

        task.setTitle(updatedTask.getTitle());

        return repository.save(task);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));

        repository.delete(task);
    }
}