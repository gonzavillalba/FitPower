package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.ExerciseRequestDTO;
import com.itec.FitPower.dto.response.ExerciseResponseDTO;
import com.itec.FitPower.util.CRUD;

public interface ExerciseService extends CRUD<ExerciseResponseDTO, ExerciseRequestDTO> {
}
