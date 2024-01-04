package com.example.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
