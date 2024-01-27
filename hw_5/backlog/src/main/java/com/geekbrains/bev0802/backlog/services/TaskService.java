package com.geekbrains.bev0802.backlog.services;

import com.geekbrains.bev0802.backlog.entities.Task;
import com.geekbrains.bev0802.backlog.enums.TaskStatus;
import com.geekbrains.bev0802.backlog.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис для обработки бизнес-логики, связанной с задачами.
 * Делегирует запросы к репозиторию и обеспечивает дополнительную логику, если это необходимо.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    /**
     * Добавляет новую задачу.
     *
     * @param task Задача для добавления.
     * @return Добавленная задача.
     */
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
    /**
     * Получает список всех задач.
     *
     * @return Список всех задач.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    /**
     * Получает задачи по их статусу.
     *
     * @param status Статус задач для поиска.
     * @return Список задач с указанным статусом.
     */
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
    /**
     * Обновляет статус задачи по идентификатору.
     *
     * @param id    Идентификатор задачи для обновления.
     * @param updatedTask  Объект задачи с новыми данными.
     * @return Обновленная задача.
     */
    public Task updateTaskStatus(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }
    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id Идентификатор задачи для удаления.     *
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
