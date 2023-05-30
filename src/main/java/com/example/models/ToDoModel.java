package com.example.models;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ToDoModel {
    @NotNull
    @Size(min = 0, max = 120)
    private String text;

    @DateTimeFormat
    private LocalDate dueDate;
    
    @NotNull
    @Pattern(regexp = "High|Medium|Low", message = "El valor no es uno de los aceptados")
    private String priority;

    @NotNull
    @DateTimeFormat
    private LocalDate creationDate;

    @DateTimeFormat
    private LocalDate doneDate;

    @NotNull
    private UUID uuid;

    @NotNull
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDate doneDate) {
        this.doneDate = doneDate;
    }

    public ToDoModel(String text, LocalDate dueDate, String priority, LocalDate creationDate) {
        this.uuid = UUID.randomUUID();
        this.text = text;
        this.dueDate = dueDate;
        this.isDone = false;
        this.doneDate = null;
        this.priority = priority;
        this.creationDate = creationDate;
    }

    public ToDoModel(String text, LocalDate dueDate, String priority, LocalDate creationDate,
            LocalDate doneDate, UUID uuid, boolean isDone) {
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
        this.creationDate = creationDate;
        this.doneDate = doneDate;
        this.uuid = uuid;
        this.isDone = isDone;
    }

    public ToDoModel() {
    }

    @Override
    public String toString() {
        return "Text: " + text + "\n" +
                "Due Date: " + dueDate + "\n" +
                "Priority: " + priority + "\n" +
                "Creation Date: " + creationDate + "\n" +
                "Done Date: " + doneDate + "\n" +
                "UUID: " + uuid + "\n" +
                "Is Done: " + isDone + "\n";
    }

}
