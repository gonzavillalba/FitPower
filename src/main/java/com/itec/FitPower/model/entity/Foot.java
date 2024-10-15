package com.itec.FitPower.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foots")
public class Foot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double calories;
    private String nutrients;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "nutritional_plan_id")
    private NutritionalPlan nutritionalPlan;

}
