package com.app.todo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.todo_app.entity.Task;
import com.app.todo_app.repository.TaskRepository;



@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return tasks;
		
	}

	public void createTask(String title) {
		Task task = new Task();
		task.setTitle(title);
		task.setCompleted(false);
		taskRepository.save(task);
		
	}

	public void deleteTask(Long id) {
		// TODO Auto-generated method stub
		taskRepository.deleteById(id);
	}

	public void toggleTask(Long id) {
		// TODO Auto-generated method stub
		Task task = taskRepository.findById(id)
				    .orElseThrow(() -> new IllegalArgumentException("Invalid id provided"));
		task.setCompleted(!task.isCompleted());
		System.out.println("Task completed value changed to :"+task.isCompleted());
		System.out.println("Task saved to DB! ");
		taskRepository.save(task);
	}
	
	

}
