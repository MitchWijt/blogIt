package com.example.blogit.lib.gcs;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Component
public class GCS {
    private final String BUCKET_NAME = "blogit";
    private final Storage storage;

    public GCS() throws Exception {
        final String PROJECT_ID = "regal-throne-319118";

        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/java/com/example/blogit/config/gcs-credentials.json"));

        this.storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(PROJECT_ID).build().getService();
    }

    public String uploadFile(String path, MultipartFile file) throws Exception {
        storage.get(BUCKET_NAME).create(path, file.getBytes());
        return getFileUrlFromFilename(path);
    }

    public String getFileUrlFromFilename(String path) {
        return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + path;
    }
}
