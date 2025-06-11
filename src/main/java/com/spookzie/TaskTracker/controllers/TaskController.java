package com.spookzie.TaskTracker.controllers;

import com.spookzie.TaskTracker.domain.dto.TaskDto;
import com.spookzie.TaskTracker.mappers.TaskMapper;
import com.spookzie.TaskTracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}