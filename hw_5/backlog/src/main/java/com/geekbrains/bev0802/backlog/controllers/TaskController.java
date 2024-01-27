package com.geekbrains.bev0802.backlog.controllers;

import com.geekbrains.bev0802.backlog.entities.Task;
import com.geekbrains.bev0802.backlog.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.geekbrains.bev0802.backlog.services.TaskService;
import java.util.List;

/**
 * Контроллер для обработки HTTP-запросов, связанных с задачами.
 * Предоставляет API для операций добавления, получения, обновления и удаления задач.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Добавляет новую задачу.
     *
     * @param task Задача для добавления.
     * @return Добавленная задача.
     *
     * пример запроса: POST http://localhost:8080/tasks
     *{
     *   "description": "приготовить завтрак",
     *   "status": "IN_PROGRESS"
     * }     *
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }
    /**
     * Возвращает список всех задач.
     *
     * @return Список всех задач.
     *
     * пример запроса: GET http://localhost:8080/tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    /**
     * Возвращает задачи по их статусу.
     *
     * @param status Статус
     * пример запроса: GET http://localhost:8080/tasks/NOT_STARTED
     **/
    @GetMapping("/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    /**
     * Обновляет статус задачи по идентификатору.
     * @param id индентификатор
     * @param updatedTask объект задачи с новыми данными
     * @return обновленная задача
     *
     * Пример запроса: PUT http://localhost:8080/tasks/1
     * {
     * "status": "COMPLETED"
     * }
     */
    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTaskStatus(id, updatedTask);
    }

    /**
     * Удаляет задачу по идентификатору.
     * @param id идентификатор
     *
     * Пример запроса: DELETE http://localhost:8080/tasks/1
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}