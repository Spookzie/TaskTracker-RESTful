package com.spookzie.TaskTracker.services.impl;

import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.repositories.TaskListRepository;
import com.spookzie.TaskTracker.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


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


    @Override
    public Optional<TaskList> getTaskListById(UUID id)
    {
        return this.taskListRepo.findById(id);
    }


    /*
    * Transactional is useful in calls like these to maintain consistency during operations on DB
    * Here we use findById() and few steps later use save() so to ensure that the DB stays consistent b/w these 2 calls it is optimal to use @Transactional
    * While save() uses transactional itself using it again doubles the security in case of exceptions
    **************************/
    @Transactional
    @Override
    public TaskList updateTaskList(UUID id, TaskList task_list)
    {
        if(task_list.getId() == null)
            throw new IllegalArgumentException("The task list must have an ID!");

        if(!Objects.equals(id, task_list.getId()))
            throw new IllegalArgumentException("Cannot change the task list ID!");

        TaskList existingTaskList = this.taskListRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));

        existingTaskList.setTitle(task_list.getTitle());
        existingTaskList.setDescription(task_list.getDescription());
        /*  No need to update the tasks as we are only changing the list's title & desc */
        /*  No need to update the created time for obv reasons  */
        existingTaskList.setUpdated(LocalDateTime.now());

        return this.taskListRepo.save(existingTaskList);
    }


    @Override
    public void deleteTaskList(UUID id)
    {
        this.taskListRepo.deleteById(id);
    }
}