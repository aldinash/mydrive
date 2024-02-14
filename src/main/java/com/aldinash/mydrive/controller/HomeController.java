package com.aldinash.mydrive.controller;


import com.aldinash.mydrive.service.NoteService;
import com.aldinash.mydrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NoteService noteService;
    private final UserService userService;

    public HomeController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    // resolve gandezh
    @GetMapping()
    public String home(Authentication authentication, Model model) throws Exception {
        String username = authentication.getName();
        model.addAttribute("notes", noteService.getAllNotes(userService.
                getUser(username).getUserId()));
        return "home";
    }
}
