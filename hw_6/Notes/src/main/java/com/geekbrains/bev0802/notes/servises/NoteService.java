package com.geekbrains.bev0802.notes.servises;

import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.Reposetories.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Сервис для управления заметками.
 * Предоставляет методы для работы с заметками, включая их поиск, сохранение и удаление.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Конструктор для создания сервиса NoteService.
     *
     * @param noteRepository Репозиторий для работы с заметками.
     */
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Получает список всех заметок.
     *
     * @return Список заметок.
     */
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Находит заметку по её идентификатору.
     *
     * @param id Идентификатор заметки.
     * @return Заметка, найденная по идентификатору.
     * @throws EntityNotFoundException если заметка с указанным идентификатором не найдена.
     */
    public Note findNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note не найдена с id: " + id));
    }

    /**
     * Сохраняет заметку в базе данных.
     *
     * @param note Заметка для сохранения.
     * @return Сохраненная заметка.
     */
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Удаляет заметку по её идентификатору.
     *
     * @param id Идентификатор заметки, которую нужно удалить.
     */
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    /**
     * Находит заметки пользователя по его идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Список заметок пользователя.
     */
    public List<Note> findNotesByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }
}

