package com.bev0802.restclient.Controller;

import com.bev0802.restclient.Entities.Note;
import com.bev0802.restclient.Entities.User;
import com.bev0802.restclient.Servises.NoteService;
import com.bev0802.restclient.Servises.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * Контроллер для обработки REST API запросов, связанных с заметками.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restService")
@RequiredArgsConstructor
public class RestController {
    private final NoteService noteService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(RestController.class);


    /**
     * Получает список всех пользователей.
     *
     * @return Список всех пользователей.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, найденный по идентификатору.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with id: {}", id);
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }


    /**
     * Создает новую заметку.
     *
     * @param note Объект заметки для сохранения.
     * @return Сохраненная заметка с присвоенным ID.
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        logger.info("Adding new note: {}", note);
        Note savedNote = noteService.saveNote(note);
        return ResponseEntity.ok(savedNote);
    }

    /**
     * Получает список всех заметок.
     *
     * @return Список всех заметок.
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        logger.info("Fetching all notes");
        List<Note> notes = noteService.findAllNotes();
        return ResponseEntity.ok(notes);
    }

    /**
     * Получает заметку по её идентификатору.
     *
     * @param id Идентификатор заметки.
     * @return Заметка, найденная по идентификатору.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        logger.info("Fetching note with id: {}", id);
        Note note = noteService.findNoteById(id);
        return ResponseEntity.ok(note);
    }

    /**
     * Обновляет существующую заметку по её идентификатору.
     *
     * @param id          Идентификатор заметки, которую нужно обновить.
     * @param noteDetails Детали заметки для обновления.
     * @return Обновленная заметка.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        logger.info("Updating note with id: {}", id);
        Note note = noteService.findNoteById(id);
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        final Note updatedNote = noteService.saveNote(note);
        return ResponseEntity.ok(updatedNote);
    }

    /**
     * Удаляет заметку по её идентификатору.
     *
     * @param id Идентификатор заметки, которую нужно удалить.
     * @return Пустой ответ, указывающий на успешное удаление.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        logger.info("Deleting note with id: {}", id);
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
