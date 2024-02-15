package com.example.Task.controllers;

import com.example.Task.domain.User;
import com.example.Task.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service;

    /**
     * Получить все задачи.
     *
     * @return         	Список задач
     */

    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }
    /**
     * Сортирует пользователей по возрасту.
     *
     * @return         	список пользователей, отсортированный по возрасту
     */

    @GetMapping("/sort")//localhost:8080/tasks/sort
    public List<User> sortUsersByAge()
    {
        return service.sortUsersByAge(service.getRepository().findAll());
    }

    /**
     * Возвращает пользователей с возрастом большим, чем заданно.
     * */
    //http://localhost:8080/tasks/filter/20
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable("age") int age){
        List<User> users = service.getRepository().findAll();
        return service.filterUsersByAge(users, age);
    }

    /**
     * Возвращает средний возраст пользователей.
     */
    @GetMapping("/calc")
    public double calculateAverageAge(){
        List<User> users = service.getRepository().findAll();
        return service.calculateAverageAge(users);
    }

}
