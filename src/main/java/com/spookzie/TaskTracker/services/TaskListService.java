package com.spookzie.TaskTracker.services;

import com.spookzie.TaskTracker.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TaskListService
{
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList task_list);

    Optional<TaskList> getTaskListById(UUID id);

    TaskList updateTaskList(UUID id, TaskList task_list);

    void deleteTaskList(UUID id);
}