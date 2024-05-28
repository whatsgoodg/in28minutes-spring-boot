package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    public int id;
    public String username;
    @Size(min=10, message="Enter at least 10 characters")
    public String description;
    public LocalDate targetDate;
    public boolean done;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public Todo(){

    }
    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetData=" + targetDate +
                ", done=" + done +
                '}';
    }
}
