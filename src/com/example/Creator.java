package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Set an id when the message is created. Persist the message to a file in json with the id as the file name.
 * static final String CRITICAL = "1";
 * static final String HIGH = "2";
 * static final String STANDARD = "3";
 * static final String LOW = "4";
 * static final String NONE = "5";
 */
public class Creator {
    //Initialize vars for the createMessages()
    private Priority messPriority = null;
    private String messDescription = null;
    private String messSender = null;
    private ObjectMapper mapper = new ObjectMapper();

    //Methods
    //createMessages()
    public void createMessages() throws IOException {
        // read input from scanner to set instance variables.
        try (Scanner s = new Scanner(System.in)) {

            System.out.println("Enter msg description -> ");

            while (true) {
                String temp = s.nextLine();
                if (messDescription == null) {
                    messDescription = temp;
                    System.out.println("Enter message Sender -> ");
                } else if (messSender == null) {
                    messSender = temp;
                    System.out.println("Enter priority enter 1 through 5");
                } else {
                    switch (temp) {
                        case "1":
                            messPriority = Priority.CRITICAL;
                            break;
                        case "2":
                            messPriority = Priority.HIGH;
                            break;
                        case "3":
                            messPriority = Priority.STANDARD;
                            break;
                        case "4":
                            messPriority = Priority.LOW;
                            break;
                        case "5":
                            messPriority = Priority.NONE;
                            break;
                        default:
                            System.out.println("need a num, 1 - 5 ");

                    }
                    System.out.println("Priority is " + messPriority);
                    /// if priority has been set
                    if (messPriority != null) {
                        //new up
                        Message mess = new Message(messPriority, messDescription, messSender);

                        System.out.println(mess);

                        //turn it into json
                        //Something like this...


                        String json = mapper.writeValueAsString(mess);
                        System.out.println("json is: " + json);

                        try (PrintWriter pw = new PrintWriter(new FileWriter(mess.getId() + ".json"))) {
                            pw.println(json);
                            pw.flush();
                        } catch (IOException e) {
                            System.out.println("Could not write " + mess.getId() + " file");
                        }
                        messPriority = null;
                        messDescription = null;
                        messSender = null;
                        System.out.println("Enter msg description -> ");
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
        creator.createMessages();
    }
}
