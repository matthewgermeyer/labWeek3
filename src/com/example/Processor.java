package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Processor {

    private ObjectMapper mapper = new ObjectMapper();

    private HashMap<Status, Set<Message>> map = new HashMap<>();

    public Processor() {
        Set<Message> messages = new HashSet<>();
        map.put(Status.INITIAL, messages);

        messages = new HashSet<>();
        map.put(Status.ASSIGNED, messages);

        messages = new HashSet<>();
        map.put(Status.IN_PROGRESS, messages);

        messages = new HashSet<>();
        map.put(Status.DONE, messages);

    }

    public void processMessages() throws IOException {
        while (true) {
            moveIt();
            System.out.println("map after moveIt is " + map);

            try {
                Thread.sleep(20_000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readIt();
            System.out.println("map after readit is " + map);

            try {
                Thread.sleep(20_000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveIt() {
        // Move all the messages from IN_PROGRESS to DONE
        Set<Message> inProgressMessages = map.get(Status.IN_PROGRESS);
        Set<Message> doneMessages = map.get(Status.DONE);
        doneMessages.addAll(inProgressMessages);

        inProgressMessages.clear();

        Set<Message> assignedMessages = map.get(Status.ASSIGNED);
        inProgressMessages.addAll(assignedMessages);

        assignedMessages.clear();

        Set<Message> initialMessages = map.get(Status.INITIAL);
        assignedMessages.addAll(initialMessages);

        initialMessages.clear();

    }

    private void readIt() throws IOException {
        File currentDirectory = new File(".");
        File files[] = currentDirectory.listFiles();
        for (File f : files) {
            if (f.getName().endsWith(".json")) {

                Path jsonFilePath = Paths.get(f.getName());
                try (BufferedReader bf = Files.newBufferedReader(jsonFilePath)) {
                    String jsonFromFile = bf.readLine();
                    Message message = mapper.readValue(jsonFromFile, Message.class);

                    if (message.getPriority() == Priority.NONE) {
                        Set<Message> messages = map.get(Status.DONE);
                        messages.add(message);
                    } else {
                        Set<Message> messages = map.get(Status.INITIAL);
                        messages.add(message);
                    }
                }

                // Delete json file
                f.delete();

            }
        }
    }

    public static void main(String args[]) throws IOException {
        Processor processor = new Processor();
        processor.processMessages();
    }
}
