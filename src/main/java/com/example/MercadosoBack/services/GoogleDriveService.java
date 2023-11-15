package com.example.MercadosoBack.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private static final String APPLICATION_NAME = "Mercadoso";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private Drive drive;

    @Autowired
    public GoogleDriveService() {
        try {
            GoogleCredentials credentials = getCredentials();
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

            this.drive = new Drive.Builder(
                    new com.google.api.client.http.javanet.NetHttpTransport(),
                    JSON_FACTORY,
                    requestInitializer
            ).setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GoogleCredentials getCredentials() throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\oscar\\OneDrive\\Documentos\\ProyectoAplicacion\\MercadosoBack\\src\\main\\resources\\mercadoso-23878b7c8200.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));
        return credentials;
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());
        java.io.File tempFile = java.io.File.createTempFile("temp", ".txt");
        multipartFile.transferTo(tempFile);

        FileContent mediaContent = new FileContent(multipartFile.getContentType(), tempFile);

        try {
            File file = drive.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            return file.getId();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            tempFile.delete();
        }
    }



    public String getDownloadLink(String fileId) throws IOException {
        File file = drive.files().get(fileId).execute();
        String webViewLink = file.getWebViewLink(); // Obtiene el enlace de vista web
        String directDownloadLink = webViewLink.replace("/view", "/uc"); // Convierte a enlace de descarga directa

        return directDownloadLink;
    }
}

