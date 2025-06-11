package com.spookzie.TaskTracker.services.impl;

import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.repositories.TaskListRepository;
import com.spookzie.TaskTracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskListServiceImpl implements TaskListService
{
    private final TaskListRepository taskListRepo;


    public TaskListServiceImpl(TaskListRepository task_list_repository)
    {
        this.taskListRepo = task_list_repository;
    }


    @Override
    public List<TaskList> listTaskLists()
    {
        return this.taskListRepo.findAll();
    }


    @Override
    public TaskList createTaskList(TaskList task_list)
    {
        if(task_list.getId() != null)
            throw new IllegalArgumentException("Task list already has an ID!");

        if(task_list.getTitle() == null || task_list.getTitle().isBlank())
            throw new IllegalArgumentException("Task list must have a title!");

        LocalDateTime now = LocalDateTime.now();

        return this.taskListRepo.save(new TaskList(
                null,
                task_list.getTitle(),
                task_list.getDescription(),
                null,
                now,
                now
        ));
    }
}