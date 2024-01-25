package com.geekbrains.bev0802.springmvc_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloWorkController {
    @RequestMapping("/hello")
    public String hello() {
        return "helloWork.html";
    }
}
