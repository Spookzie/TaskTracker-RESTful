package com.spookzie.TaskTracker.domain.dto;

import java.util.List;
import java.util.UUID;


/*
 * Provides the boilerplate code for the DTO- constructors, getters, etc.
 * Does NOT provide the setters as all the variables here are immutable
 **************************/
public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}