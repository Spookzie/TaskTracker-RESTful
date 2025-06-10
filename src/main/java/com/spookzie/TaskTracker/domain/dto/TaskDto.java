package com.spookzie.TaskTracker.domain.dto;

import com.spookzie.TaskTracker.domain.entities.TaskPriority;
import com.spookzie.TaskTracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


/*
* Provides the boilerplate code for the DTO- constructors, getters, etc.
* Does NOT provide the setters as all the variables here are immutable
**************************/
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
){
}