package com.geekbrains.bev0802.security_web_boot;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private-data")

public class PrivateDataController {
    @GetMapping
    @Secured("ROLE_ADMIN") // Убедитесь, что ваша роль начинается с "ROLE_"
    public String privateDataPage() {
        return "private-data"; // Возвращает имя view (например, Thymeleaf или JSP страницы)
    }
}
