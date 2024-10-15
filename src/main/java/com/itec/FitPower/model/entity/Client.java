package com.itec.FitPower.model.entity;

import com.itec.FitPower.util.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "clients")
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ClientStatus initState;
    @OneToOne
    private ClientStatus currentState;
    private String goal;
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    private Nutritionist nutritionist;
    @OneToMany(mappedBy = "client")
    private List<Routine>routines = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<NutritionalPlan>nutritionalPlans = new ArrayList<>();
    @OneToOne
    private Record record;
    @OneToMany(mappedBy = "client")
    private List<TrainingDiary>trainingDiaryList = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<NutritionalDiary>nutritionalDiaryList = new ArrayList<>();


}
