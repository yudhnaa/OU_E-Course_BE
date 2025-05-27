package com.ou.helpers;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleDriveHelper {
    @Autowired
    private Drive googleDrive;

    public Map<String, String> uploadFile(File file) throws Exception {
        com.google.api.services.drive.model.File newFile = new com.google.api.services.drive.model.File();
        newFile.setName(file.getName());

        String mimeType = detectMimeType(file);

        FileContent mediaContent = new FileContent(mimeType, file);

        com.google.api.services.drive.model.File uploadedFile = googleDrive.files().create(newFile, mediaContent).setFields("id,webViewLink").execute();

        googleDrive.permissions().create(uploadedFile.getId(), getPermission()).execute();

        Map<String, String> result = new HashMap<String, String>();
        result.put("publicId", uploadedFile.getId());
        result.put("url", uploadedFile.getWebViewLink());

        return result;
    }

    public Map<String, String> uploadFile(MultipartFile multipartFile) throws Exception {
        com.google.api.services.drive.model.File newFile = new com.google.api.services.drive.model.File();
        newFile.setName(multipartFile.getOriginalFilename());

        String mimeType = detectMimeType(multipartFile);

        InputStreamContent mediaContent = new InputStreamContent(mimeType, multipartFile.getInputStream());

        com.google.api.services.drive.model.File uploadedFile = googleDrive.files()
                .create(newFile, mediaContent)
                .setFields("id,webViewLink")
                .execute();

        googleDrive.permissions().create(uploadedFile.getId(), getPermission()).execute();

        Map<String, String> result = new HashMap<>();
        result.put("publicId", uploadedFile.getId());
        result.put("url", uploadedFile.getWebViewLink());

        return result;
    }

    public boolean deleteFile(String fileId) {
        try {
            googleDrive.files().delete(fileId).execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Permission getPermission() {
        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");
        return permission;
    }

    public static String detectMimeType(File file) {
        try {
            Path path = file.toPath();
            String mimeType = Files.probeContentType(path);

            return mimeType != null ? mimeType : "application/octet-stream";
        } catch (Exception ignored) {
            return "application/octet-stream";
        }
    }

    public String detectMimeType(MultipartFile file) {
        try {
            String mimeType = file.getContentType();
            return mimeType != null ? mimeType : "application/octet-stream";
        } catch (Exception e) {
            return "application/octet-stream";
        }
    }
}
