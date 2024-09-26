package com.example.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @PrePersist
    public void setDefaultRole() {
        if (this.role == null) {
            this.role = new Role();
            this.role.setId(1L); // Assuming 1L is the ID of the default role in the Role table
        }
    }
}