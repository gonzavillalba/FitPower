package com.itec.FitPower.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "training_diary")
public class TrainingDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Client client;
    private LocalDateTime date;
    private String observation;
    private boolean completed;
    @OneToMany(mappedBy = "trainingDiary")
    private List<Session>exercisesPerformed = new ArrayList<>();
}
