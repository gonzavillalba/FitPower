package com.itec.FitPower.model.service;



import com.itec.FitPower.dto.request.GymRequestDTO;
import com.itec.FitPower.dto.response.AddClientToNutritionistResponseDTO;
import com.itec.FitPower.dto.response.AddClientToTrainerResponseDTO;
import com.itec.FitPower.dto.response.GymResponseDTO;
import com.itec.FitPower.model.entity.Gym;

import java.util.List;
import java.util.Optional;

public interface GymService {
    GymResponseDTO create(GymRequestDTO gymRequestDto);
    public List<GymResponseDTO> findAll();
    public Optional<Gym> findByName(String name);
    Gym getGymByNameOrThrow(String name);
    public GymResponseDTO update(GymRequestDTO gymRequestDto, Long id);
    public void deleteByName(String name);

    GymResponseDTO addClientToGym(String gymCode, String dni);

    GymResponseDTO addTrainerToGym(String gymCode, String dni);

    GymResponseDTO addNutritionistToGym(String gymCode, String dni);

    AddClientToTrainerResponseDTO assignTrainerToClient(String dniTrainer, String dniClient);

    AddClientToNutritionistResponseDTO assignNutritionistToClient(String dniNutritionist, String dniClient);


}
