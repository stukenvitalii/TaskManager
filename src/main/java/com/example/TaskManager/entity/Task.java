package com.example.TaskManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "task_user_id", nullable = false)
    private User user;
}
