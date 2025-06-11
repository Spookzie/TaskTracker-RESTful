package com.spookzie.TaskTracker.repositories;

import com.spookzie.TaskTracker.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>
{
    /*  Get all the tasks in a list */
    List<Task> findByTaskListId(UUID task_list_id);

    /*  Get a specific task in a task list  */
    Optional<Task> findByTaskListIdAndId(UUID task_list_id, UUID id);

    /*  Delete a specific task in a task list   */
    void deleteByTaskListIdAndId(UUID task_list_id, UUID id);
}