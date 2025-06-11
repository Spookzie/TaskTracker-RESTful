package com.spookzie.TaskTracker.controllers;

import com.spookzie.TaskTracker.domain.dto.TaskListDto;
import com.spookzie.TaskTracker.domain.entities.TaskList;
import com.spookzie.TaskTracker.mappers.TaskListMapper;
import com.spookzie.TaskTracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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


    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID id)
    {
        return this.taskListService.getTaskListById(id)
                .map(this.taskListMapper::toDto);
    }


    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDto task_list_dto)
    {
        TaskList updatedTaskList = this.taskListService.updateTaskList(
                id,
                this.taskListMapper.fromDto(task_list_dto)
        );

        return this.taskListMapper.toDto(updatedTaskList);
    }


    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id)
    {
        this.taskListService.deleteTaskList(id);
    }
}