package com.itec.FitPower.model.entity;

public enum StagnationType {
    PHYSICAL("estancamiento fisico"),
    NUTRITIONAL("estancamiento nutricional");

    private final String type;

    StagnationType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return type;
    }
}
