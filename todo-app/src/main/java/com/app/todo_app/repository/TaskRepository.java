package com.app.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.todo_app.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	

}
