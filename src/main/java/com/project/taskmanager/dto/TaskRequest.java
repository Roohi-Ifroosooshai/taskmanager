package com.project.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}