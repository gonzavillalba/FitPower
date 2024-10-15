package com.itec.FitPower.dto.response;

import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.entity.Trainer;
import lombok.Data;

import java.util.List;

@Data
public class GymResponseDto {
    private String gymCode;
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<Client> clientList;
    private List<Trainer>trainerList;
    private List<Nutritionist>nutritionistList;
}
