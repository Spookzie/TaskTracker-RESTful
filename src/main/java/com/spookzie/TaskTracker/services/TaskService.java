package com.spookzie.TaskTracker.services;

import com.spookzie.TaskTracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;


public interface TaskService
{
    List<Task> listTasks(UUID task_list_id);

    Task createTask(UUID task_list_id, Task task);
}