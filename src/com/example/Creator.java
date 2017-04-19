package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Creator {

    private Scanner scanner = new Scanner(System.in);

    private ObjectMapper mapper = new ObjectMapper();

    public void createMessages() throws IOException {

        String description = null;
        String sender = null;
        Priority priority = null;

        System.out.printf("Input description\n");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (description == null) {
                description = input;
                System.out.println("Input sender\n");
            } else if (sender == null) {
                sender = input;
                System.out.println("Input priority\n");
            } else {
                try {
                    priority = Priority.valueOf(input);

                    Message message = new Message(priority, description, sender);
                    String json = mapper.writeValueAsString(message);
                    Path jsonFilePath = Paths.get(message.getId() + ".json");
                    try (BufferedWriter bf = Files.newBufferedWriter(jsonFilePath)) {
                        bf.write(json);
                        bf.flush();
                    }

                    description = null;
                    sender = null;
                    priority = null;
                    System.out.printf("Input description\n");

                } catch(IllegalArgumentException e) {
                    System.out.println("Input priority again\n");
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
        creator.createMessages();
    }
}