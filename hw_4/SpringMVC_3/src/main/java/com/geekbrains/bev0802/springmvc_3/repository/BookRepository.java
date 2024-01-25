package com.geekbrains.bev0802.springmvc_3.repository;

import com.geekbrains.bev0802.springmvc_3.models.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Репозиторий для управления книгами.
 */
@Component
public class BookRepository {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();
    /**
     * Находит все книги в списке.
     *
     * @return         список всех книг
     */
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
    /**
     * Находит книгу по ее ID.
     *
     * @param  id  ID книги для поиска
     * @return     книга с указанным ID или null, если не найдена
     */
    public Book findById(long id) {
        return books.get(id);
    }

    /**
     * Сохраняет объект книги.
     *
     * @param  book  книга, которую нужно сохранить
     * @return       сохраненная книга
     */
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(counter.incrementAndGet());
        }
        books.put(book.getId(), book);
        return book;
    }
}
