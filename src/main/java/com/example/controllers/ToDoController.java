package com.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import com.example.models.CreateModel;
import com.example.models.FilterModel;
import com.example.models.ToDoModel;
import com.example.services.ToDoServiceDB;
import com.example.utils.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.validation.Valid;

@RestController
@Validated
public class ToDoController {
    ToDoServiceDB db;

    public ToDoController() {
        this.db = new ToDoServiceDB();
    }

    public ToDoController(ToDoServiceDB db) {
        this.db = db;
    }

    // A GET endpoint (/todos) to list “to do’s”
    // Include pagination. Pages should be of 10 elements.
    // Sort by priority and/or due date
    // Filter by done/undone
    // Filter by the name or part of the name
    // Filter by priority
    @GetMapping("/todos")
    public ResponseEntity<Object> getTodo(
            @Valid @ModelAttribute FilterModel requestParams) {
        // System.out.println(requestParams);
        List<ToDoModel> result = db.getFiltered(requestParams);
        System.out.println(result);
        return CustomResponse.generateResponse(HttpStatus.OK, result);
    }

    // A POST endpoint (/todos) to create “to do’s”
    @PostMapping(value = "/todos")
    @Validated
    public ResponseEntity<Object> createTodo(@Valid @RequestBody CreateModel requestData) {
        db.addToDo(requestData);

        // Crear un objeto JSON con la cantidad de elementos
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseBody = objectMapper.createObjectNode();
        responseBody.put("response", String.valueOf(db.todos.size()));

        return CustomResponse.generateResponse(HttpStatus.OK, responseBody);
    }

    // A PUT endpoint (/todos/{id}) to update the “to do” name, due date and/or
    // priority
    // Validations included
    @PutMapping(value = "/todos/{id}")
    public ResponseEntity<Object> updateTodo(@Valid @RequestBody ToDoModel update, @PathVariable("id") UUID id) {

        ToDoModel response = db.updateToDoModel(id, update);
        if (response == null)
            return CustomResponse.generateResponse(HttpStatus.BAD_REQUEST, response);

        // Crear un objeto JSON con la cantidad de elementos
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseBody = objectMapper.createObjectNode();
        responseBody.put("update", response.toString());
        responseBody.put("todos", db.todos.toString());

        return CustomResponse.generateResponse(HttpStatus.OK, responseBody);
    }

    // A POST endpoint (/todos/{id}/done) to mark “to do” as done
    // This should update the “done date” property
    // If “to do” is already done nothing should happen (no error returned)
    @PostMapping(value = "/todos/{id}/done")
    public ResponseEntity<Object> updateTodoDone(@PathVariable("id") UUID id) {
        ToDoModel actualizado = db.markDoneUndone(id, true);
        if (actualizado == null)
            return CustomResponse.generateResponse(HttpStatus.BAD_REQUEST, actualizado);

        // Crear un objeto JSON con la cantidad de elementos
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseBody = objectMapper.createObjectNode();
        responseBody.put("update", actualizado.toString());
        // responseBody.put("todos", db.todos.toString());

        return CustomResponse.generateResponse(HttpStatus.OK, responseBody);
    }

    @PutMapping(value = "/todos/{id}/undone")
    public ResponseEntity<Object> updateTodoUndone(@PathVariable("id") UUID id) {
        ToDoModel actualizado = db.markDoneUndone(id, false);
        if (actualizado == null)
            return CustomResponse.generateResponse(HttpStatus.BAD_REQUEST, actualizado);

        // Crear un objeto JSON con la cantidad de elementos
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseBody = objectMapper.createObjectNode();
        responseBody.put("update", actualizado.toString());
        // responseBody.put("todos", db.todos.toString());

        return CustomResponse.generateResponse(HttpStatus.OK, responseBody);
    }
}
