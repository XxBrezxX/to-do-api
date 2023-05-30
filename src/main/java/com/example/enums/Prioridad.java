package com.example.enums;

public enum Prioridad {
    HIGH("h"),
    MEDIUM("m"),
    LOW("l");

    private String prioridad;

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    Prioridad(String prioridad){
        this.prioridad = prioridad;
    }

    
}
