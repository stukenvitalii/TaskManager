package com.example.TaskManager.controller;

import com.example.TaskManager.entity.Task;
import com.example.TaskManager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String createTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task",task);
        return "create_task";
    }

    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTaskForm(@PathVariable Long id,
                               Model model) {
        model.addAttribute("task",taskService.getTaskById(id));
        return "edit_task";
    }

    @PostMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id,
                           @ModelAttribute("task") Task task,
                           Model model) {
        Task existingTask = taskService.getTaskById(id);
        existingTask.setId(task.getId());
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());

        taskService.updateTask(existingTask);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }



}
