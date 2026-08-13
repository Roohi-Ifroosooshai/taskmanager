package com.project.taskmanager.service;

import com.project.taskmanager.dto.TaskRequest;
import com.project.taskmanager.entity.Task;
import com.project.taskmanager.exception.TaskNotFoundException;
import com.project.taskmanager.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger =
            LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {

        logger.info("Fetching all tasks");

        List<Task> tasks = repository.findAll();

        logger.info("Fetched {} tasks", tasks.size());

        return tasks;
    }

    @Override
    public Page<Task> getTasks(
            int page,
            int size,
            String sortBy,
            String direction) {

        logger.info(
                "Fetching paged tasks: page={}, size={}, sortBy={}, direction={}",
                page, size, sortBy, direction
        );

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sortBy)
        );

        Page<Task> result = repository.findAll(pageable);

        logger.info(
                "Paged query returned {} tasks",
                result.getNumberOfElements()
        );

        return result;
    }

    @Override
    public Page<Task> searchTasks(
            String title,
            int page,
            int size,
            String sortBy,
            String direction) {

        logger.info(
                "Searching tasks: title={}, page={}, size={}, sortBy={}, direction={}",
                title, page, size, sortBy, direction
        );

        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sortBy)
        );

        Page<Task> result =
                repository.findByTitleContainingIgnoreCase(
                        title,
                        pageable
                );

        logger.info(
                "Search returned {} tasks",
                result.getNumberOfElements()
        );

        return result;
    }

    @Override
    public Task getTaskById(Long id) {

        logger.info("Fetching task with id={}", id);

        return repository.findById(id)
                .orElseThrow(() -> {

                    logger.warn(
                            "Task not found with id={}",
                            id
                    );

                    return new TaskNotFoundException(
                            "Task not found with id: " + id
                    );
                });
    }

    @Override
    public Task createTask(TaskRequest request) {

        logger.info(
                "Creating task with title={}",
                request.getTitle()
        );

        Task task = new Task();
        task.setTitle(request.getTitle());

        Task savedTask = repository.save(task);

        logger.info(
                "Task created successfully with id={}",
                savedTask.getId()
        );

        return savedTask;
    }

    @Override
    public Task updateTask(
            Long id,
            TaskRequest updatedTask) {

        logger.info("Updating task with id={}", id);

        Task task = repository.findById(id)
                .orElseThrow(() -> {

                    logger.warn(
                            "Update failed. Task not found with id={}",
                            id
                    );

                    return new TaskNotFoundException(
                            "Task not found with id: " + id
                    );
                });

        task.setTitle(updatedTask.getTitle());

        Task savedTask = repository.save(task);

        logger.info(
                "Task updated successfully with id={}",
                id
        );

        return savedTask;
    }

    @Override
    public void deleteTask(Long id) {

        logger.info("Deleting task with id={}", id);

        Task task = repository.findById(id)
                .orElseThrow(() -> {

                    logger.warn(
                            "Delete failed. Task not found with id={}",
                            id
                    );

                    return new TaskNotFoundException(
                            "Task not found with id: " + id
                    );
                });

        repository.delete(task);

        logger.info(
                "Task deleted successfully with id={}",
                id
        );
    }
}


