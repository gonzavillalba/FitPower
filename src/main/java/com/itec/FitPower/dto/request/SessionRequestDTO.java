package com.itec.FitPower.dto.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SessionRequestDTO {
    private Long id;
    private String trainingDay;
    private String muscleGroup;
    private int sets;
    private int reps;
    private LocalTime restTime;
    private LocalTime duration;
    private boolean completed;
    private String exerciseName;
    private String routineName;

}
