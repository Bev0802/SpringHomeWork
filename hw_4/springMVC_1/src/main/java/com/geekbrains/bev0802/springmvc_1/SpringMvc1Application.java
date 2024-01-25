package com.geekbrains.bev0802.springmvc_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. Создание базового веб-приложения Spring MVC
 * Начните с создания простого веб-приложения с использованием Spring MVC.
 * Это может быть простой сайт, который выводит "Привет, мир!" на главной странице.
 * Используйте аннотацию @Controller и @RequestMapping для маршрутизации запросов на эту страницу.
 * */

@SpringBootApplication
public class SpringMvc1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvc1Application.class, args);
    }

}
