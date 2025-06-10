package com.spookzie.TaskTracker.mappers;

import com.spookzie.TaskTracker.domain.dto.TaskDto;
import com.spookzie.TaskTracker.domain.entities.Task;


public interface TaskMapper
{
    Task fromDto(TaskDto task_dto);

    TaskDto toDto(Task task);
}