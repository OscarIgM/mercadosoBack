package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.services.GoogleDriveService;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/google-drive")
public class GoogleDriveController {

@Autowired
GoogleDriveService googleDriveService;


    @PostMapping("/upload")
    public String uploadFileToGoogleDrive(@RequestParam("file") MultipartFile file) {
        try {
            String fileId = googleDriveService.uploadBasic(file);
            return fileId;//"File uploaded with ID: " +
        } catch (IOException e) {
            return "Failed to upload the file to Google Drive: " + e.getMessage();
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/obtainImage")
    public ByteArrayOutputStream obtainImage(@RequestParam String id){
        try {
            return googleDriveService.downloadFile(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
