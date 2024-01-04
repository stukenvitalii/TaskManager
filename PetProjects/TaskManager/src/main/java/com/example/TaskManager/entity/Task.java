package com.example.TaskManager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long id;

    @Column(name = "task_name",nullable = false)
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "task_status")
    private String status;

    public Task() {

    }

    public Task(String name,String description,String status) {
        super();
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
