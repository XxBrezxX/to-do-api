package com.example.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.models.CreateModel;
import com.example.models.FilterModel;
import com.example.models.ToDoModel;
import com.example.utils.comparators.*;

public class ToDoServiceDB {
    public List<ToDoModel> todos;

    public ToDoServiceDB() {
        this.todos = new ArrayList<>();
    }

    public ToDoServiceDB(List<ToDoModel> todos) {
        this.todos = todos;
    }

    public void addToDo(CreateModel toCreate) {
        ToDoModel todo = new ToDoModel(
                toCreate.getText(),
                toCreate.getDueDate(),
                toCreate.getPriority(),
                toCreate.getCreationDate());

        todos.add(todo);
    }

    public List<ToDoModel> getFiltered(FilterModel filters) {
        if (filters.getPrioritySort() != null) {
            if (filters.getPrioritySort()) {
                Collections.sort(todos, new PriorityComparatorUp());
            } else if (!filters.getPrioritySort()) {
                Collections.sort(todos, new PriorityComparatorDown());
            }
            return todos;
        }
        if (filters.getDueDateSort() != null) {
            if (filters.getDueDateSort()) {
                Collections.sort(todos, Comparator.comparing(ToDoModel::getDueDate).reversed());
            } else if (!filters.getDueDateSort()) {
                Collections.sort(todos, Comparator.comparing(ToDoModel::getDueDate));
            }
            return todos;
        }
        if (filters.getIsDone() != null) {
            List<ToDoModel> filtered = new ArrayList<>();
            if (filters.getIsDone() == "Done") {
                filtered = todos.stream().filter(todo -> todo.isDone()).collect(Collectors.toList());
            } else if (filters.getIsDone() == "Undone") {
                filtered = todos.stream().filter(todo -> !todo.isDone()).collect(Collectors.toList());
            }
            return filtered;
        }
        if (filters.getName() != null) {
            List<ToDoModel> filtered = todos.stream().filter(item -> item.getText().contains(filters.getName()))
                    .collect(Collectors.toList());
            return filtered;
        }
        if (filters.getPriority() != null) {
            List<ToDoModel> filtered = todos.stream().filter(item -> item.getPriority() == filters.getPriority())
                    .collect(Collectors.toList());
            return filtered;
        }

        return todos;
    }

    public ToDoModel markDoneUndone(UUID uuid, boolean toDone){
        for (int i = 0; i < todos.size(); i++) {
            ToDoModel objeto = todos.get(i);
            if (objeto.getUuid().equals(uuid)) {
                objeto.setDone(toDone);
                todos.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }

    public ToDoModel updateToDoModel(UUID uuid, ToDoModel update){
        for (int i = 0; i < todos.size(); i++) {
            ToDoModel objeto = todos.get(i);
            if (objeto.getUuid().equals(uuid)) {
                todos.set(i, update);
                return objeto;
            }
        }
        return null;
    }
}
