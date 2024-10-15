package com.itec.FitPower.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingDay;
    private String muscleGroup;
    private int sets;
    private int reps;
    private LocalTime restTime;
    private LocalTime duration;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "routine_id")
    @JsonBackReference  // Rompemos la referencia para evitar la recursión
    private Routine routine;
    @ManyToOne
    @JoinColumn(name = "training_diary_id")
    @JsonBackReference  // Rompemos la referencia para evitar la recursión
    private TrainingDiary trainingDiary;

}


