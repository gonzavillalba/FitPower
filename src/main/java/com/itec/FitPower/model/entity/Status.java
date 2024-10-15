package com.itec.FitPower.model.entity;


public enum Status {
    STAGNANT("estado estancado"),
    ACTIVE("estado activo"),
    DISABLE("estado inactivo");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
