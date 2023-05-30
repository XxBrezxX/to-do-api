package com.example.utils.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.example.models.ToDoModel;

public class PriorityComparatorDown implements Comparator<ToDoModel> {
    private Map<String, Integer> valueOrder = new HashMap<>();

    public PriorityComparatorDown() {
        valueOrder.put("Low", 3);
        valueOrder.put("Medium", 2);
        valueOrder.put("High", 1);
    }

    @Override
    public int compare(ToDoModel arg0, ToDoModel arg1) {
        Integer order1 = valueOrder.get(arg0.getPriority());
        Integer order2 = valueOrder.get(arg1.getPriority());
        return order2.compareTo(order1);
    }
}