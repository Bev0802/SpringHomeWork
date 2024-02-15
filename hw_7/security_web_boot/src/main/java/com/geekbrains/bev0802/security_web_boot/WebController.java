package com.geekbrains.bev0802.security_web_boot;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {

    /**
     * Метод для обработки корневого URL. Возвращает главную страницу.
     */
    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("message", "Hello, World!");
        return "index"; // возвращает имя представления (например, index.html)
    }

    /**
     * Метод для обработки запроса на получение публичных данных.
     */
    @GetMapping("/public-data")
    public String publicData(Model model) {
        model.addAttribute("message", "Это публичные данные доступные всем пользователям.");
        return "public-data"; // возвращает имя представления для публичных данных
    }

    /**
     * Метод для обработки запроса на получение приватных данных.
     */
    @GetMapping("/private-data")
    public String privateData(Model model) {
        model.addAttribute("message", "Это приватные данные доступные только администратору.");
        return "private-data"; // возвращает имя представления для приватных данных
    }

    /**
     * Метод для обработки запроса на страницу входа.
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "Это страница входа.");
        return "login"; // возвращает имя представления для страницы входа
    }

    /**
     * Метод для обработки запроса на страницу ошибки доступа.
     */
    @GetMapping("/access_denied")
    public String accessDenied(Model model) {
        return "access_denied"; // возвращает имя представления для страницы ошибки доступа
    }
}
