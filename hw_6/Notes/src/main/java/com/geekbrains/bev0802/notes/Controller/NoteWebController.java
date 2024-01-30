package com.geekbrains.bev0802.notes.Controller;

import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.Entities.User;
import com.geekbrains.bev0802.notes.servises.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/notes")
public class NoteWebController {
    private NoteService noteService;

    @Autowired
    public void NoteWebController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public String getNotesByUserId(@PathVariable Long id, Model model) {
        model.addAttribute("notes", noteService.findNotesByUserId(id));
        return "notes"; // имя HTML-шаблона (например, notes.html)
    }

    @PostMapping("/add/{userId}")
    public String addNote(@ModelAttribute Note note, @PathVariable Long userId) {
        noteService.saveNote(note);
        return "redirect:/notes";
    }
}
