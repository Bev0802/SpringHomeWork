package com.geekbrains.bev0802.security_web_boot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public-data")
public class Controller {

    @GetMapping
    public String publicDataPage() {
        return "public-data"; // Имя view для отображения
    }
}