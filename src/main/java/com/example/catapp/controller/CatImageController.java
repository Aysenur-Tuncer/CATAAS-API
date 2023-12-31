package com.example.catapp.controller;

import com.example.catapp.service.CatImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatImageController {

    @Autowired
    private CatImageService catImageService;

    @GetMapping("/tag")
    public String getRandomCatWithTag(@RequestParam String tag) {
        String endpoint = "cat/" + tag;
        catImageService.downloadCatImage(endpoint);
        return "Cat image saved successfully!";
    }

    @GetMapping("/text")
    public String getRandomCatWithText(@RequestParam String text) {
        String endpoint = "cat/says/" + text;
        catImageService.downloadCatImage(endpoint);
        return "Cat image saved successfully!";
    }

    @GetMapping("/dimensions")
    public String getRandomCatWithDimensions(@RequestParam int width, @RequestParam int height) {
        String endpoint = String.format("cat?width=%d&height=%d", width, height);
        catImageService.downloadCatImage(endpoint);
        return "Cat image saved successfully!";
    }
}
