package com.aldinash.mydrive.controller;

import com.aldinash.mydrive.model.Note;
import com.aldinash.mydrive.model.User;
import com.aldinash.mydrive.service.NoteService;
import com.aldinash.mydrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {
    final private NoteService noteService;
    final private UserService userService;

    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication authentication, Note note) {
        String username = authentication.getName();
        User user = userService.getUser(username);
        if (note.getNoteid() > 0) {
            noteService.updateNote(note);
        } else {
            noteService.addNote(note, user.getUserId());
        }
        return "redirect:result?success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        if (noteid > 0) {
            noteService.deleteNote(noteid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
