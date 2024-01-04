package com.example.TaskManager.service;

import com.example.TaskManager.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task saveTask(Task task);
    Task getTaskById(Long id);
    Task updateTask(Task task);

    void deleteTaskById(Long id);
}
