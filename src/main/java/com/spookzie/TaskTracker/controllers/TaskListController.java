package com.spookzie.TaskTracker.controllers;

import com.spookzie.TaskTracker.domain.dto.TaskListDto;
import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.mappers.TaskListMapper;
import com.spookzie.TaskTracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController
{
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;


    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper)
    {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> listTaskLists()
    {
        return this.taskListService.listTaskLists()
                .stream()
                .map(this.taskListMapper::toDto)
                .toList();
    }


    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto task_list_dto)
    {
        return this.taskListMapper.toDto(
                this.taskListService.createTaskList(
                        this.taskListMapper.fromDto(task_list_dto)
                )
        );
    }
}