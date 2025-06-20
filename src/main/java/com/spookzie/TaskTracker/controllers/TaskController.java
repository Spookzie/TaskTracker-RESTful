package com.spookzie.TaskTracker.controllers;

import com.spookzie.TaskTracker.domain.dto.TaskDto;
import com.spookzie.TaskTracker.mappers.TaskMapper;
import com.spookzie.TaskTracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TaskController
{
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService task_service, TaskMapper task_mapper)
    {
        this.taskService = task_service;
        this.taskMapper = task_mapper;
    }


    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID task_list_id)
    {
        return this.taskService.listTasks(task_list_id)
                .stream().map(this.taskMapper::toDto)
                .toList();
    }


    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id")UUID task_list_id, @RequestBody TaskDto task_dto)
    {
        return this.taskMapper.toDto(
                this.taskService.createTask(
                        task_list_id,
                        this.taskMapper.fromDto(task_dto)
                )
        );
    }


    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTaskById(@PathVariable("task_list_id")UUID task_list_id, @PathVariable("task_id")UUID task_id)
    {
        return this.taskService.getTaskById(task_list_id, task_id)
                .map(this.taskMapper::toDto);
    }


    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id")UUID task_list_id, @PathVariable("task_id")UUID task_id,
                              @RequestBody TaskDto task_dto)
    {
        return this.taskMapper.toDto(
                this.taskService.updateTask(
                        task_list_id, task_id,
                        this.taskMapper.fromDto(task_dto)
                )
        );
    }


    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable("task_list_id")UUID task_list_id, @PathVariable("task_id") UUID task_id)
    {
        this.taskService.deleteTask(task_list_id, task_id);
    }
}