package com.project.taskmanager.controller;

import com.project.taskmanager.entity.Task;
import com.project.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.project.taskmanager.dto.TaskRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task API",
        description = "CRUD Operations for Tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public Task createTask(@Valid @RequestBody TaskRequest request) {
        return service.createTask(request);
    }

    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @Valid @RequestBody TaskRequest request) {
        return service.updateTask(id, request);
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "Task deleted successfully";
    }
}