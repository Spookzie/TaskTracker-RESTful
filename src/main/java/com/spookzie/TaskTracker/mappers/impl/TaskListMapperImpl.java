package com.spookzie.TaskTracker.mappers.impl;

import com.spookzie.TaskTracker.domain.dto.TaskListDto;
import com.spookzie.TaskTracker.domain.entities.Task;
import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.domain.entities.TaskStatus;
import com.spookzie.TaskTracker.mappers.TaskListMapper;
import com.spookzie.TaskTracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;
import org.w3c.dom.css.Counter;

import java.util.List;
import java.util.Optional;


@Component
public class TaskListMapperImpl implements TaskListMapper
{
    private final TaskMapper taskMapper;


    public TaskListMapperImpl(TaskMapper task_mapper)
    {
        this.taskMapper = task_mapper;
    }


    @Override
    public TaskList fromDto(TaskListDto task_list_dto)
    {
        return new TaskList(
                task_list_dto.id(),
                task_list_dto.title(),
                task_list_dto.description(),

                Optional.ofNullable(task_list_dto.tasks())
                        .map(taskDtos ->taskDtos.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),

                null,
                null
        );
    }


    @Override
    public TaskListDto toDto(TaskList task_list)
    {
        return new TaskListDto(
                task_list.getId(),
                task_list.getTitle(),
                task_list.getDescription(),

                Optional.ofNullable(task_list.getTasks())
                        .map(List::size)
                        .orElse(0),

                calculateTaskListProgress(task_list.getTasks()),

                Optional.ofNullable(task_list.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList()
                        ).orElse(null)
        );
    }

    /*
    * Returns a value b/w 0 & 1
    * Calculates the fraction of tasks closed vs total number of tasks
    ***************************/
    private Double calculateTaskListProgress(List<Task> tasks)
    {
        if(tasks == null)
                return null;

        long closedTaskCount = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED).count();

        return (double) closedTaskCount / tasks.size();
    }
}