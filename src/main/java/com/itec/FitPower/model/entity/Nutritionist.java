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
@Table(name = "nutritionists")
public class Nutritionist extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profession;
    private boolean available;
    private boolean active;
    @OneToMany(mappedBy = "nutritionist")
    private List<NutritionalPlan> nutritionalPlanList;

    @OneToMany(mappedBy = "nutritionist")
    private List<Client> clients;
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


}
