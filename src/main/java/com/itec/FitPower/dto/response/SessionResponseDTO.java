package com.itec.FitPower.dto.response;


import lombok.Data;

import java.time.LocalTime;

@Data
public class SessionResponseDTO {
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
