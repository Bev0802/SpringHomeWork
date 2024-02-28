package com.geekbrains.bev0802.notes.Controller;

import com.geekbrains.bev0802.notes.Entities.User;
import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.servises.FileGateway;
import com.geekbrains.bev0802.notes.servises.NoteService;
import com.geekbrains.bev0802.notes.servises.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class WebController {
    private final UserService userService;
    private final NoteService noteService;
    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    private final FileGateway fileGateway;


    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String showAddFormUsers(Model model) {
        model.addAttribute("user", new User());
        return "usercreate";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute User user) {
        User savedUser = userService.saveUser(user);
        // Записываем информацию о пользователе в файл
        String userInfo = String.format("New User: %s, ID: %s", savedUser.getName(), savedUser.getId());
        fileGateway.writeToFile("user_info.txt", userInfo);

        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/update")
    public String showUpdateUserForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "userupdate";
    }

    @PutMapping("/users/update/{userId}")
    public String updateUser(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        User existingUser = userService.findUserById(userId);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setPassword(updatedUser.getPassword());
            userService.saveUser(existingUser);
            logger.info("Пользователь обновлен: {}", existingUser);
        } else {
            logger.error("Пользователь с ID {} не найден", userId);
        }
        return "redirect:/users";
    }

    @DeleteMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return "redirect:/users";
    }


    // Notes
    @GetMapping("/users/{userId}/notes")
    public String showAddFormNotes(@PathVariable Long userId, Model model) {
        List<Note> notes = noteService.findNotesByUserId(userId);
        model.addAttribute("notes", notes);
        User user = userService.findUserById(userId);
        model.addAttribute("user", user); // Добавить пользователя в модель для отображения его имени
        return "notes";
    }

    @GetMapping("/users/{userId}/notes/add")
    public String showAddNoteForm(@PathVariable Long userId, Model model) {
        User user = userService.findUserById(userId); // Assuming you have a userService to find the user
        model.addAttribute("user", user);
        Note newNote = new Note();
        newNote.setUser(user);
        model.addAttribute("note", newNote);
        return "noteadd";
    }

    @PostMapping("/users/{userId}/notes/add")
    public String addNote(@PathVariable Long userId, @ModelAttribute Note note) {
        note.setUser(userService.findUserById(userId));
        Note savedNote = noteService.saveNote(note);
        // Записываем информацию о заметке в файл
        String noteInfo = String.format("New Note: %s, ID: %s, User ID: %s", savedNote.getTitle(), savedNote.getId(), userId);
        fileGateway.writeToFile("note_info.txt", noteInfo);

        return "redirect:/users/" + userId + "/notes";
    }

    @GetMapping("/users/{userId}/notes/{noteId}/update")
    public String showUpdateNoteForm(@PathVariable Long userId, @PathVariable Long noteId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        Note existingNote = noteService.getNoteById(noteId);
        model.addAttribute("note", existingNote);
        return "noteupdate";
    }


    @PutMapping("/users/{userId}/notes/{noteId}/update")
    public String updateNote(@PathVariable Long userId, @PathVariable Long noteId, @ModelAttribute Note note) {
        logger.info("Обновление заметки с ID: {} для пользователя с ID: {}", noteId, userId);
        logger.debug("Данные заметки: {}", note);
        note.setUser(userService.findUserById(userId));
        note.setId(noteId);
        noteService.saveNote(note);
        logger.info("Заметка обновлена: {}", note);
        return "redirect:/users/" + userId + "/notes";
    }

    @DeleteMapping("/users/{userId}/notes/{noteId}/delete")
    public String deleteNote(@PathVariable Long userId,
                             @PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/users/" + userId + "/notes";
    }
}
