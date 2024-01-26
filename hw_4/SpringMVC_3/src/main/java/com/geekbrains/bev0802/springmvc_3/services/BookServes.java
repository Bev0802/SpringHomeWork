package com.geekbrains.bev0802.springmvc_3.services;

import com.geekbrains.bev0802.springmvc_3.models.Book;
import com.geekbrains.bev0802.springmvc_3.repository.BookRepository;
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
            bookRepository.save(new Book(null, "Евгений Онегин", "Пушкин"));
            bookRepository.save(new Book(null, "Мастер и Маргарита", "М.Булгаков"));
            bookRepository.save(new Book(null, "Вечера на хуторе близ Диканьки", "Гоголь"));
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

    /**
     * Добавляет книгу в список.
     */
    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void editBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
