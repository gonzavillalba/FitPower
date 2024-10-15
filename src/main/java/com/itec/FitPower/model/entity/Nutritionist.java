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
    private String profession;
    private boolean available;
    @OneToMany(mappedBy = "nutritionist")
    private List<NutritionalPlan> nutritionalPlanList = new ArrayList<>();
    @OneToMany(mappedBy = "nutritionist")
    private List<Client>clients = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


}
