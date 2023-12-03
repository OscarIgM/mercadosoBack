package com.example.MercadosoBack.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
@Service
public class GoogleDriveService {

    public String uploadBasic(MultipartFile file) throws IOException {
        // Load pre-authorized user credentials from the environment.
        // TODO(developer) - See https://developers.google.com/identity for
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();

        // Upload file photo.jpg on drive.
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());

        // Use InputStream from MultipartFile directly
        InputStreamContent mediaContent = new InputStreamContent(file.getContentType(), file.getInputStream());

        try {
            // Execute the request to upload the file.
            File file2 = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();

            System.out.println("File ID: " + file2.getId());
            return file2.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }


//descargar archivo
    public ByteArrayOutputStream downloadFile(String realFileId) throws IOException {
        /* Load pre-authorized user credentials from the environment.
           TODO(developer) - See https://developers.google.com/identity for
          guides on implementing OAuth2 for your application.*/
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();

        try {
            OutputStream outputStream = new ByteArrayOutputStream();
            service.files().get(realFileId)
                    .executeMediaAndDownloadTo(outputStream);

            return (ByteArrayOutputStream) outputStream;
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to move file: " + e.getDetails());
            throw e;
        }


    }
    public static void saveByteArrayToFile(byte[] byteArray, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);
        }
    }
    public static void convert(String id) throws IOException {
        GoogleDriveService googleDriveService=new GoogleDriveService();
        ByteArrayOutputStream byteArrayOutputStream = googleDriveService.downloadFile(id);
        saveByteArrayToFile(byteArrayOutputStream.toByteArray(), "C:\\Users\\oscar\\OneDrive\\Escritorio\\maravillosoFront\\output.jpg");
        System.out.println("Archivo guardado exitosamente.");
    }
}