package com.geekbrains.bev0802.notes.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.creationDate = now;
        this.lastModifiedDate = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}