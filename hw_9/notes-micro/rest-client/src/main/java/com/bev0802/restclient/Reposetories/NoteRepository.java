package com.bev0802.restclient.Reposetories;

import com.bev0802.restclient.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с заметками.
 * Предоставляет доступ к операциям CRUD для сущности Note.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Находит заметки по идентификатору пользователя.
     *
     * @param userId Идентификатор пользователя, заметки которого нужно найти.
     * @return Список заметок, принадлежащих пользователю с заданным идентификатором.
     */

    List<Note> findNotesByUserId(Long userId);

    /**
     * Находит заметки пользователя по его идентификатору.
     * Этот метод аналогичен методу findNotesByUserId и предоставляет тот же функционал.
     *
     * @param userId Идентификатор пользователя, заметки которого нужно найти.
     * @return Список заметок, принадлежащих пользователю с заданным идентификатором.
     */
    List<Note> findByUserId(Long userId);
}
