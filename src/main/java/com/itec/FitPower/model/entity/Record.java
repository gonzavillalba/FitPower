package com.itec.FitPower.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "record")
    private List<Progress>progresses = new ArrayList<>();
    @OneToMany(mappedBy = "record")
    private List<Stagnation>stagnationList = new ArrayList<>();

}
