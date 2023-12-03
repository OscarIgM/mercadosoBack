package com.example.MercadosoBack.controllers;

import com.example.MercadosoBack.services.GoogleDriveService;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "/obtainImage", produces = "image/jpeg")
    public ResponseEntity<byte[]> obtainImage(@RequestParam String id) {
        try {
            ByteArrayOutputStream imageBytes = googleDriveService.downloadFile(id);
            byte[] imageByteArray = imageBytes.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageByteArray.length);
            return new ResponseEntity<>(imageByteArray, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
