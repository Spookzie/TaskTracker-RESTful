package com.spookzie.TaskTracker.repositories;

import com.spookzie.TaskTracker.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID>
{

}