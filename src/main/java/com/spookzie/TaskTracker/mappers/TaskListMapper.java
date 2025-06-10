package com.spookzie.TaskTracker.mappers;

import com.spookzie.TaskTracker.domain.dto.TaskListDto;
import com.spookzie.TaskTracker.domain.entities.TaskList;


public interface TaskListMapper
{
    TaskList fromDto(TaskListDto task_list_dto);

    TaskListDto toDto(TaskList task_list);
}