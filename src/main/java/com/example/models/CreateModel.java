package com.example.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateModel {
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

    public CreateModel(String text, LocalDate dueDate, String priority, LocalDate creationDate) {
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
        this.creationDate = creationDate;
    }

    public CreateModel() {
    }

}
