package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.services.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/google-drive")
public class GoogleDriveController {
    private final GoogleDriveService googleDriveService;

    @Autowired
    public GoogleDriveController(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    @PostMapping("/upload")
    public String uploadFileToGoogleDrive(@RequestParam("file") MultipartFile file) {
        try {
            String fileId = googleDriveService.uploadFile(file);
            return "File uploaded with ID: " + fileId;
        } catch (IOException e) {
            return "Failed to upload the file to Google Drive: " + e.getMessage();
        }
    }

    @GetMapping("/getDownloadLink")
    public String getDownloadLink(@RequestParam String fileId) {
        try {
            String directDownloadLink = googleDriveService.getDownloadLink(fileId);
            return directDownloadLink;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al obtener el enlace de descarga.";
        }
    }
}
