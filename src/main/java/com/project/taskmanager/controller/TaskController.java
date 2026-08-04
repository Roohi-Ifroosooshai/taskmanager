package com.project.taskmanager.controller;

import com.project.taskmanager.entity.Task;
import com.project.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.project.taskmanager.dto.TaskRequest;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping
    public Task createTask(
            @Valid @RequestBody TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());

        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());

        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "Task deleted successfully";
    }
}