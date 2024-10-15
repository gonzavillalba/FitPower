package com.itec.FitPower.dto.response;

import com.itec.FitPower.model.entity.Exercise;
import com.itec.FitPower.model.entity.Routine;
import com.itec.FitPower.model.entity.TrainingDiary;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SessionResponseDto {
    private Long id;
    private String trainingDay;
    private String muscleGroup;
    private int sets;
    private int reps;
    private LocalTime restTime;
    private LocalTime duration;
    private boolean completed;
    private Exercise exercise;
    private Routine routine;
    private TrainingDiary trainingDiary;
}
