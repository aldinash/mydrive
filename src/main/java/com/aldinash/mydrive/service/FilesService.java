package com.aldinash.mydrive.service;

import com.aldinash.mydrive.mapper.FilesMapper;
import com.aldinash.mydrive.model.Files;
import com.aldinash.mydrive.model.ResponseFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FilesService {

    private final FilesMapper filesMapper;

    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public ResponseFile getResponseFile(Files file) {
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
        return new ResponseFile(file.getFileid(), file.getFilename(), dataURL);
    }

    public List<ResponseFile> getAllFiles(int userid) throws Exception {
        List<Files> files = filesMapper.findByUserId(userid);
        if (files == null) {
            throw new Exception();
        }
        List<ResponseFile> responseFiles = new ArrayList<>();
        for (Files file : files) {
            ResponseFile responseFile = getResponseFile(file);
            responseFiles.add(responseFile);
        }
        return responseFiles;
    }

    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        Files file = new Files();
        try {
            file.setContenttype(fileUpload.getContentType());
            file.setFiledata(fileUpload.getBytes());
            file.setFilename(fileUpload.getOriginalFilename());
            file.setFilesize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        filesMapper.insertFile(file, userid);
    }

    public void deleteFile(int fileid) {
        filesMapper.deleteFile(fileid);
    }
}