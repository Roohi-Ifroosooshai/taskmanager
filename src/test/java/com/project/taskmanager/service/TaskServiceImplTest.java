package com.project.taskmanager.service;

import com.project.taskmanager.dto.TaskRequest;
import com.project.taskmanager.entity.Task;
import com.project.taskmanager.exception.TaskNotFoundException;
import com.project.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllTasks() {

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Learn Java");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Learn Spring Boot");

        when(taskRepository.findAll())
                .thenReturn(List.of(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());

        verify(taskRepository, times(1))
                .findAll();
    }

    @Test
    void shouldCreateTask() {

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Mockito");

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Mockito");

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        Task savedTask = taskService.createTask(taskRequest);

        assertEquals("Mockito", savedTask.getTitle());

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }

    @Test
    void shouldReturnTaskById() {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Spring Boot");

        when(taskRepository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals("Spring Boot",
                result.getTitle());

        verify(taskRepository, times(1))
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        when(taskRepository.findById(99L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(99L)
        );
    }

    @Test
    void shouldDeleteTask() {

        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository, times(1))
                .delete(task);
    }



}
