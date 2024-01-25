package com.geekbrains.bev0802.springmvc_2.controllers;

import org.springframework.ui.Model;
import com.geekbrains.bev0802.springmvc_2.services.BookServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  * Контроллер для обработки запросов, связанных с книгами.
 *  *
 *  * @param  model   модель, которая будет передана представлению
 *  * @return        имя представления для отображения списка книг
 */

@Controller
@RequestMapping("/library")
public class BookController {
    @Autowired
    private BookServes bookServes;

    @GetMapping()
    public String bookslist(Model model) {
        model.addAttribute("books", bookServes.getAllBooks());
        return "library";
    }

}
