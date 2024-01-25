package com.geekbrains.bev0802.springmvc_2.services;

import com.geekbrains.bev0802.springmvc_2.models.Book;
import com.geekbrains.bev0802.springmvc_2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервиса для управления книгами.
 */
@Service
public class BookServes {
    @Autowired
    BookRepository bookRepository;

    /**
     * Получает все книги и сохраняет несколько новых.
     *
     * @return список всех книг
     */
    public List<Book> getAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        if (allBooks.isEmpty()) {
            bookRepository.save(new Book(null, "Война и мир", "Л.Толстой"));
            bookRepository.save(new Book(null, "Преступление и наказание", "Достоевский"));
            bookRepository.save(new Book(null, "Евгений Онегин", "Л.Толстой"));
            bookRepository.save(new Book(null, "Мастер и Маргарита", "М.Булгаков"));
            bookRepository.save(new Book(null, "Вечера на хуторе близ Диканьки", "Л.Толстой"));
            bookRepository.save(new Book(null, "Приключения Шерлока Холмса", "А.К. Дойл"));
            allBooks = bookRepository.findAll();
        }
        return allBooks;
    }

    /**
     * Получает книгу по ее ID.
     *
     * @param id ID книги для получения
     * @return книга с заданным ID
     */
    public Book getBook(long id){
        return bookRepository.findById(id);
    }
}
