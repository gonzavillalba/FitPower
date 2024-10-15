package com.itec.FitPower.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference  // Rompemos la referencia para evitar la recursión
    private Client client;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonBackReference  // Rompemos la referencia para evitar la recursión
    private Trainer trainer;
    private String routineCode;
    private String routineType;
    private LocalDate creationDate;
    private  LocalDate startDate;
    private boolean active;
    @OneToMany(mappedBy = "routine")
    @JsonManagedReference  // Marcamos la relación principal
    private List<Session>sessions = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Status status;
}
