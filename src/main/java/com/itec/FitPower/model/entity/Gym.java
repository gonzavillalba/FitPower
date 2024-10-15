package com.itec.FitPower.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String gymCode;
    private String name;
    private String phone;
    private String email;
    private String address;
    @OneToMany(mappedBy = "gym")
    @JsonManagedReference  // Marcamos la relación principal
    private List<Client>clientList = new ArrayList<>();
    @OneToMany(mappedBy = "gym")
    @JsonManagedReference  // Marcamos la relación principal
    private List<Trainer>trainerList = new ArrayList<>();
    @OneToMany(mappedBy = "gym")
    @JsonManagedReference  // Marcamos la relación principal
    private List<Nutritionist>nutritionistList = new ArrayList<>();

}
