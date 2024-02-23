package com.aldinash.mydrive.controller;


import com.aldinash.mydrive.model.User;
import com.aldinash.mydrive.service.FilesService;
import com.aldinash.mydrive.service.NoteService;
import com.aldinash.mydrive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NoteService noteService;
    private final UserService userService;
    private final FilesService filesService;

    public HomeController(NoteService noteService, UserService userService, FilesService filesService) {
        this.noteService = noteService;
        this.userService = userService;
        this.filesService = filesService;
    }

    // resolve gandezh
    @GetMapping()
    public ModelAndView getHomePage(Authentication authentication) throws  Exception {
        String username = authentication.getName();
        User user = userService.getUser(username);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("notes", noteService.getAllNotes(user.getUserId()));
        modelAndView.addObject("files", filesService.getAllFiles(user.getUserId()));
        return modelAndView;
    }
}
