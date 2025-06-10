package com.spookzie.TaskTracker.mappers.impl;

import com.spookzie.TaskTracker.domain.dto.TaskDto;
import com.spookzie.TaskTracker.domain.entities.Task;
import com.spookzie.TaskTracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;


@Component
public class TaskMapperImpl implements TaskMapper
{
    @Override
    public Task fromDto(TaskDto task_dto)
    {
        return new Task(
                task_dto.id(),
                task_dto.title(),
                task_dto.description(),
                task_dto.dueDate(),
                task_dto.status(),
                task_dto.priority(),
                null,
                null,
                null
        );
    }


    @Override
    public TaskDto toDto(Task task)
    {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority()
        );
    }
}