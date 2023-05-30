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
        List<ToDoModel> order = this.todos;
        if (filters.getPrioritySort() != null) {
            System.out.println("Entre a prioridad con valor" + filters.getPrioritySort().toString());
            if (filters.getPrioritySort()) {
                Collections.sort(order, new PriorityComparatorUp());
            } else if (!filters.getPrioritySort()) {
                Collections.sort(order, new PriorityComparatorDown());
            }
        }
        if (filters.getDueDateSort() != null) {
            System.out.println("Entre a DueDate con valor " + filters.getDueDateSort().toString());
            if (filters.getDueDateSort()) {
                Collections.sort(order, Comparator.comparing(ToDoModel::getDueDate).reversed());
            } else if (!filters.getDueDateSort()) {
                Collections.sort(order, Comparator.comparing(ToDoModel::getDueDate));
            }
        }
        if (filters.getIsDone() != null) {
            System.out.println("Entre a isDone con valor: " + filters.getIsDone());
            if (filters.getIsDone().equals("Done")) {
                System.out.println("Entre a A");
                List<ToDoModel> orderUpdate = new ArrayList<>();
                orderUpdate = order.stream().filter(todoElement -> todoElement.isDone()).collect(Collectors.toList());
                order = orderUpdate;
            } else if (filters.getIsDone().equals("Undone")) {
                List<ToDoModel> orderUpdate = new ArrayList<>();
                orderUpdate = order.stream().filter(todoElement -> !todoElement.isDone()).collect(Collectors.toList());
                order = orderUpdate;
            }
        }
        if (filters.getName() != null) {
            System.out.println("Entre a getname con valor: " + filters.getName());
            List<ToDoModel> orderUpdate = new ArrayList<>();
            orderUpdate = order.stream().filter(item -> item.getText().contains(filters.getName()))
                    .collect(Collectors.toList());
            order = orderUpdate;
        }
        if (filters.getPriority() != null) {
            System.out.println("Entre a getprio con valor: " + filters.getPriority());
            List<ToDoModel> orderUpdate = new ArrayList<>();
            orderUpdate = this.todos.stream().filter(item -> item.getPriority().equals(filters.getPriority()))
                    .collect(Collectors.toList());
            order = orderUpdate;
        }
        return order;
    }

    public ToDoModel markDoneUndone(UUID uuid, boolean toDone) {
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

    public ToDoModel updateToDoModel(UUID uuid, ToDoModel update) {
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
