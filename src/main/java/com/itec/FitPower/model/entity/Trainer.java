package com.itec.FitPower.model.entity;

import com.itec.FitPower.util.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "trainers")
public class Trainer extends Person {
    private String profession;
    private boolean available;
    @OneToMany(mappedBy = "trainer")
    private List<Routine>routineList = new ArrayList<>();
    @OneToMany(mappedBy = "trainer")
    private List<Client>clients = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

}
