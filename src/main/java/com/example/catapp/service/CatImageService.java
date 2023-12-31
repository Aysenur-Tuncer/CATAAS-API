package com.example.catapp.service;

import com.example.catapp.entity.CatImage;
import com.example.catapp.repository.CatImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class CatImageService {

    @Value("${download.directory}")
    private String downloadDirectory;

    @Autowired
    private CatImageRepository catImageRepository;

    public void saveCatImage(String fileName, String filePath, long fileSize) {
        CatImage catImage = new CatImage();
        catImage.setFileName(fileName);
        catImage.setFilePath(filePath);
        catImage.setFileSize(fileSize);

        catImageRepository.save(catImage);
    }

    public void downloadCatImage(String endpoint) {
        try {
            URL url = new URL("https://cataas.com/" + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Ask the user for the filename
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the filename to save as: ");
                String filename = scanner.nextLine() + ".jpg";

                // Save the image to the specified directory
                File existingFile = new File(downloadDirectory, filename);
                if (existingFile.exists()) {
                    System.out.println("File with the same name already exists. Choose a different filename.");
                } else {
                    saveImageToFile(connection.getInputStream(), existingFile);
                    System.out.println("Cat image saved successfully!");

                    // Save the image details to the database
                    saveCatImage(filename, existingFile.getAbsolutePath(), existingFile.length());
                }
            } else {
                System.out.println("Failed to get cat image. HTTP response code: " + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImageToFile(InputStream inputStream, File file) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}