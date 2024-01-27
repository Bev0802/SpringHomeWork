package com.geekbrains.bev0802.backlog.entities;


import com.geekbrains.bev0802.backlog.enums.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;

/**
 * Класс Task представляет сущность задачи в системе управления задачами.
 * Содержит информацию о задаче, включая описание, статус и дату создания.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    /**
     * Идентификатор задачи
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Описание задачи
     */
    @NotBlank
    @Column(nullable = false)
    private String description;

    /**
     * Статус задачи
     */
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /**
     * Дата и время создания задачи
     */
    @Column(updatable = false)
    private LocalDateTime creationDate;

    /**
     * Метод, аннотированный @PrePersist, для установки даты создания
     * перед сохранением сущности.
     *
     */
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}
