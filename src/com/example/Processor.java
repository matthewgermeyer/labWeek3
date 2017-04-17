package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * create a set of messages, add it to the map for the first few sets of messages.
 * <p>
 * //take that set and put it into the map for status.
 * <p>
 * Set<Message> set = new HashSet<>();
 * map.put(Status.INITIAL, set);
 * <p>
 * set = new HashSet<>();
 * map.put(Status.ASSIGNED, set);
 * <p>
 * /*
 * read json -> object mapper turns to a message ->
 * if (mess.getPriority.NONE != none){
 * <p>
 * map.get(Status.INITIAL.add(mess);  // read as go get all the messsages
 * // for INITIAL and add this one to it.
 * }
 * else{
 * map.get(Status.Done).add(mess);
 * <p>
 * }
 */

/*
 Have a map with status as the key and a set of messages for the value
 sout map

 Check for new messages (files). For each new message
 reads the file into a Message object
 removes the file

 sout the message

 if the priority is NONE puts the message in the DONE map entry, otherwise puts the message in the INITIAL map entry.
 */

public class Processor {

    public void processMessages() throws InterruptedException {
        while (true) {
            moveIt();
            readIt();
            Thread.sleep(500L);
            Set<Message> set = new HashSet<>[];
            Map<Status, Set<Message> map = new HashMap<Status, Set<Message>();
            Set<Message> set = new HashSet<>();

        }

    }

    private void moveIt() {
        // move messages in map from one state to another
        map.put(Status.INITIAL, set);

        set = new HashSet<>();
        map.put(Status.ASSIGNED, set);
        /*
        move messages from one map entry to the next (i.e., From INITIAL to ASSIGNED; from ASSIGNED TO IN_PROGRESS; from IN_PROGRESS to DONE). A message should only transitioned once per loop.

            sout map
         */
    }

    private void readIt() {
        // read the json files into Messages and put in map
        StringBuilder rows = new StringBuilder();
        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                try (BufferedReader bf = new BufferedReader(new FileReader(f.getName()))) {
                    String json = bf.readLine();
                    Message mess = mapper.readValue(json, Message.class);
                    System.out.println("Mess is " + mess);
                }

            public static void main (String args[]) throws InterruptedException {
                Processor processor = new Processor();
                processor.processMessages();
            }
        }
        }