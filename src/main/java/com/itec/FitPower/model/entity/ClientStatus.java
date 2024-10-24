package com.itec.FitPower.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client_status")
public class ClientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight;
    private Double height;
    private Double bodyMass;
    private Double bodyFat;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
