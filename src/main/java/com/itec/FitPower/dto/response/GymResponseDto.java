package com.itec.FitPower.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GymResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<ClientResponseDTO> clientList;
    private List<TrainerResponseDto>trainerList;
    private List<NutritionistResponseDto>nutritionistList;
}
