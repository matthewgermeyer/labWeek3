package com.example;

import java.util.Random;

/**
 * Created by MattyG on 4/14/17.
 */
public class Message {
    private int id;
    private Priority priority;
    private String description;
    private String senderName;

    private Random randy = new Random();

    public Message(Priority priority, String description, String senderName) {
        this.id = Math.abs(randy.nextInt());
        this.priority = priority;
        this.description = description;
        this.senderName = senderName;
    }

    public int getId() {
        return id;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getSenderName() {
        return senderName;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id='" + id + '\'' +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
