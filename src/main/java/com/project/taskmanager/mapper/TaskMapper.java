package com.project.taskmanager.mapper;

import com.project.taskmanager.dto.TaskResponse;
import com.project.taskmanager.entity.Task;

public final class TaskMapper {

    private TaskMapper() {
    }

    public static TaskResponse toResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser() != null
                        ? task.getUser().getId()
                        : null
        );
    }
}