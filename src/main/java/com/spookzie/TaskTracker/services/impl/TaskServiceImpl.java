package com.spookzie.TaskTracker.services.impl;

import com.spookzie.TaskTracker.domain.entities.Task;
import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.domain.entities.TaskPriority;
import com.spookzie.TaskTracker.domain.entities.TaskStatus;
import com.spookzie.TaskTracker.repositories.TaskListRepository;
import com.spookzie.TaskTracker.repositories.TaskRepository;
import com.spookzie.TaskTracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class TaskServiceImpl implements TaskService
{
    private final TaskRepository taskRepo;
    private final TaskListRepository taskListRepo;

    public TaskServiceImpl(TaskRepository task_repo, TaskListRepository task_list_repo)
    {
        this.taskRepo = task_repo;
        this.taskListRepo = task_list_repo;
    }


    @Override
    public List<Task> listTasks(UUID task_list_id)
    {
        return this.taskRepo.findByTaskListId(task_list_id);
    }


    /*  Refer updateTaskList() in TaskListServiceImpl.java to see why transactional is used */
    @Transactional
    @Override
    public Task createTask(UUID task_list_id, Task task)
    {
        if(task.getId() != null)
            throw new IllegalArgumentException("The task already has an ID!");

        if(task.getTitle() == null || task.getTitle().isBlank())
            throw new IllegalArgumentException("The task must have a title!");

        /*  Handling all the not nullable data of task  */
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskList taskList = this.taskListRepo.findById(task_list_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task list ID provided!"));

        LocalDateTime now = LocalDateTime.now();


        return this.taskRepo.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        ));
    }


    @Override
    public Optional<Task> getTaskById(UUID task_list_id, UUID task_id)
    {
        return this.taskRepo.findByTaskListIdAndId(task_list_id, task_id);
    }


    @Transactional
    @Override
    public Task updateTask(UUID task_list_id, UUID task_id, Task task)
    {
        if(task_list_id == null)
            throw new IllegalArgumentException("The task list must have an ID!");

        if(!Objects.equals(task_id, task.getId()))
            throw new IllegalArgumentException("Cannot change the task ID!");

        if(task.getStatus() == null)
            throw new IllegalArgumentException("Task must have a valid status!");

        if(task.getPriority() == null)
            throw new IllegalArgumentException("Task must have a valid priority!");


        Task existingTask = this.taskRepo.findByTaskListIdAndId(task_list_id, task_id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdated(LocalDateTime.now());

        return this.taskRepo.save(existingTask);
    }


    @Transactional
    @Override
    public void deleteTask(UUID task_list_id, UUID task_id)
    {
        this.taskRepo.deleteByTaskListIdAndId(task_list_id, task_id);
    }
}