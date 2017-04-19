package com.example;

import java.util.Random;

public class Message {
    private int id;
    private Priority priority;
    private String description;
    private String senderName;

    private Random random = new Random();

    public Message() {
    }

    public Message(Priority priority, String description, String senderName) {
        this.priority = priority;
        this.description = description;
        this.senderName = senderName;
        this.id = Math.abs(random.nextInt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (priority != message.priority) return false;
        if (description != null ? !description.equals(message.description) : message.description != null) return false;
        return senderName != null ? senderName.equals(message.senderName) : message.senderName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}