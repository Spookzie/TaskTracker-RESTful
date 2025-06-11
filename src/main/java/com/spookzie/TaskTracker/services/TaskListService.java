package com.spookzie.TaskTracker.services;

import com.spookzie.TaskTracker.domain.entities.TaskList;

import java.util.List;


public interface TaskListService
{
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList task_list);
}