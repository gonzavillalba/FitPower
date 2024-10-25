package com.itec.FitPower.dto.request;

import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.entity.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymRequestDTO {
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<Client> clientList;
    private List<Trainer>trainerList;
    private List<Nutritionist>nutritionistList;
}