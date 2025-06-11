package com.spookzie.TaskTracker.services;

import com.spookzie.TaskTracker.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TaskService
{
    List<Task> listTasks(UUID task_list_id);

    Task createTask(UUID task_list_id, Task task);

    Optional<Task> getTaskById(UUID task_list_id, UUID task_id);

    Task updateTask(UUID task_list_id, UUID task_id, Task task);

    void deleteTask(UUID task_list_id, UUID task_id);
}