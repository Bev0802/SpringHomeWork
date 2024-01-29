package com.geekbrains.bev0802.notes.Controller;

import com.geekbrains.bev0802.notes.Entities.User;
import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.servises.NoteService;
import com.geekbrains.bev0802.notes.servises.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserWebController {
    private final UserService userService;
    private final NoteService noteService;
    private final Logger logger = LoggerFactory.getLogger(UserWebController.class);

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
        userService.saveUser(user);
        return "redirect:/users";
    }
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
        Note savedNote = noteService.saveNote(note);
        return "redirect:/users/" + userId + "/notes";
    }

}
