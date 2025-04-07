package com.app.todo_app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.todo_app.entity.Task;
import com.app.todo_app.service.TaskService;

@Controller
@RequestMapping("/task-home")
public class TaskController {
	
	private final TaskService taskService;
	
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	@GetMapping
	public String getAllTasks(Model model) {
		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasksList", tasks);
		tasks.forEach(task -> logger.info("Fetched task with id :"+ task.getId()+ ", title : "+task.getTitle()+ ", completed : "+task.isCompleted()));
		return "tasks";
	}
	
	@GetMapping("/add-task")
	public String addTask() {
		return "addTask";
	}
	
	@PostMapping("/create-task")
	public String createTask(@RequestParam String title) {
		taskService.createTask(title);
		logger.info("Created task and redirected to task home page with title :"+title);
		return "redirect:/task-home";
	}
	
	@GetMapping("/{id}/delete")
	public String createTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		logger.info("Deleted task and redirected to task home page with id :"+id);
		return "redirect:/task-home";
	}
	
	@GetMapping("/{id}/toggle")
	public String toggleTask(@PathVariable Long id) {
		taskService.toggleTask(id);
		logger.info("Task toggled and redirected to task home page with id :"+id);
		return "redirect:/task-home";
	}
	
	

}
