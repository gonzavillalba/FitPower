package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.RoutineRequestDTO;
import com.itec.FitPower.dto.request.TrainerRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.RoutineResponseDTO;
import com.itec.FitPower.dto.response.TrainerResponseDTO;
import com.itec.FitPower.model.entity.Trainer;
import com.itec.FitPower.util.CRUD;

import java.util.List;

public interface TrainerService extends CRUD<TrainerResponseDTO, TrainerRequestDTO> {
    TrainerResponseDTO create(TrainerRequestDTO trainerRequestDto);
    List<TrainerResponseDTO> findAll();

    Trainer getTrainerByDniOrThrow(String dni);

    TrainerResponseDTO findByDni(String dni);
    TrainerResponseDTO update(TrainerRequestDTO trainerRequestDto);

    // Elimina un entrenador por su ID

    void deleteByDni(String dni);

    List<ClientResponseDTO> getClientsAssociated(String dni);

    RoutineResponseDTO createRoutine(RoutineRequestDTO routineRequestDto);

}
