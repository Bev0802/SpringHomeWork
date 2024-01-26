package com.geekbrains.bev0802.springmvc_3.controllers;

import com.geekbrains.bev0802.springmvc_3.models.Book;
import com.geekbrains.bev0802.springmvc_3.services.BookServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Метод для получения списка книг.
     *
     * @param  model  модель, к которой добавляется список книг
     * @return       html страницу представления для списка книг
     */
    @GetMapping()
    public String bookslist(Model model) {
        model.addAttribute("books", bookServes.getAllBooks());
        return "library";
    }

    /**
     * Получить книгу по ID.
     *
     * @param  id    ID книги
     * @param  model модель
     * @return       html страницу представления для книги
     */
    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookServes.getBook(id));
        return "book";
    }
    /**
     +     * Получает форму добавления книги.
     +     *
     +     * @return         строку "addbook"
     +     */
    @GetMapping("/addbook")
    public String showAddBookForm() {
        return "addbook";
    }
    /**
     * Добавление книги
     * @param book - книга
     * @param model - модель
     * @return html страницу ввода данных
     */
    @PostMapping("/addbook")
    public String addNewBook(@ModelAttribute Book book, Model model) {
        bookServes.addBook(book);
        model.addAttribute("books", bookServes.getAllBooks());
        return "redirect:/library";
    }
    @GetMapping("/editbook/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
            model.addAttribute("book", bookServes.getBook(id));
        return "editbook";
    }

    @PostMapping("/editbook")
    public String editBook(@ModelAttribute Book book, Model model) {
        bookServes.editBook(book);
        model.addAttribute("books", bookServes.getAllBooks());
        return "redirect:/library";
    }

    @PostMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookServes.deleteBook(id);
        model.addAttribute("books", bookServes.getAllBooks());
        return "redirect:/library";
    }


}
