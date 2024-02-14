package com.aldinash.mydrive.controller;

import com.aldinash.mydrive.model.Note;
import com.aldinash.mydrive.model.User;
import com.aldinash.mydrive.service.NoteService;
import com.aldinash.mydrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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
        if (note.getNoteid() != null) {
            noteService.editNote(note);
        } else {
            noteService.createNote(note, user.getUserId());
        }
        return "redirect:result?success";
    }
}
