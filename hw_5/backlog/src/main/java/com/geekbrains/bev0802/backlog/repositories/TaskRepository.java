package com.geekbrains.bev0802.backlog.repositories;

import com.geekbrains.bev0802.backlog.entities.Task;
import com.geekbrains.bev0802.backlog.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Интерфейс TaskRepository предоставляет методы для взаимодействия с базой данных задач.
 * Наследуется от JpaRepository для обеспечения CRUD-операций.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}