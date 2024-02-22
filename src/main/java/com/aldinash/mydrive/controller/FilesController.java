package com.aldinash.mydrive.controller;

import com.aldinash.mydrive.model.User;
import com.aldinash.mydrive.service.FilesService;
import com.aldinash.mydrive.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FilesController {

    private final FilesService filesService;
    private final UserService userService;

    public FilesController(FilesService filesService, UserService userService) {
        this.filesService = filesService;
        this.userService = userService;
    }

    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        String username = authentication.getName();
        User user = userService.getUser(username);
        if (fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        filesService.addFile(fileUpload, user.getUserId());
        return "redirect:/result?success";
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("id") int fileid) {
        if (fileid > 0) {
            filesService.deleteFile(fileid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
