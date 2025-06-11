package com.spookzie.TaskTracker.services.impl;

import com.spookzie.TaskTracker.domain.entities.Task;
import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.domain.entities.TaskPriority;
import com.spookzie.TaskTracker.domain.entities.TaskStatus;
import com.spookzie.TaskTracker.repositories.TaskListRepository;
import com.spookzie.TaskTracker.repositories.TaskRepository;
import com.spookzie.TaskTracker.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
}