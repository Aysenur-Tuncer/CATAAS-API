package com.example.catapp;

import com.example.catapp.controller.CatImageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@SpringBootApplication
public class CatAppApplication {

    @Autowired
    private CatImageController catImageController;

    public static void main(String[] args) {
        SpringApplication.run(CatAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Get a random cat with a tag");
                System.out.println("2. Get a random cat with text");
                System.out.println("3. Get a random cat with user-defined width and height");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter tag for the cat: ");
                        String tag = scanner.nextLine();
                        catImageController.getRandomCatWithTag(tag);
                        break;
                    case 2:
                        System.out.print("Enter text for the cat: ");
                        String text = scanner.nextLine();
                        catImageController.getRandomCatWithText(text);
                        break;
                    case 3:
                        System.out.print("Enter width for the cat: ");
                        int width = scanner.nextInt();
                        System.out.print("Enter height for the cat: ");
                        int height = scanner.nextInt();
                        catImageController.getRandomCatWithDimensions(width, height);
                        break;
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            }
        };
    }
}