package com.project.taskmanager.exception;

import com.project.taskmanager.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleTaskNotFound(
            TaskNotFoundException ex) {

        return new ErrorResponse(
                404,
                ex.getMessage());
    }
}