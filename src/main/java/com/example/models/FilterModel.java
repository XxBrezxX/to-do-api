package com.example.models;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.*;

public class FilterModel {
    @NotNull
    private Integer page;

    @Nullable
    private Boolean prioritySort;

    @Nullable
    private Boolean dueDateSort;

    @Nullable
    @Pattern(regexp = "Done|Undone", message = "El valor no es uno de los aceptados")
    private String isDone;

    @Nullable
    @Size(min = 0, max = 120)
    private String name;

    @Nullable
    @Pattern(regexp = "High|Medium|Low", message = "El valor no es uno de los aceptados")
    private String priority;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getPrioritySort() {
        return prioritySort;
    }

    public void setPrioritySort(Boolean prioritySort) {
        this.prioritySort = prioritySort;
    }

    public Boolean getDueDateSort() {
        return dueDateSort;
    }

    public void setDueDateSort(Boolean dueDateSort) {
        this.dueDateSort = dueDateSort;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public FilterModel(Integer page, Boolean prioritySort, Boolean dueDateSort,
            @Pattern(regexp = "Done|Undone", message = "El valor no es uno de los aceptados") String isDone,
            @Size(min = 0, max = 120) String name,
            @Pattern(regexp = "High|Medium|Low", message = "El valor no es uno de los aceptados") String priority) {
        this.page = page;
        this.prioritySort = prioritySort;
        this.dueDateSort = dueDateSort;
        this.isDone = isDone;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "page=" + page +
                ", prioritySort=" + prioritySort +
                ", dueDateSort=" + dueDateSort +
                ", isDone='" + isDone + '\'' +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

}
