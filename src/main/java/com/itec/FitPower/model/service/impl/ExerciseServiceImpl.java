package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.ExerciseRequestDTO;
import com.itec.FitPower.dto.response.ExerciseResponseDTO;
import com.itec.FitPower.model.service.ExerciseService;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {
    @Override
    public ExerciseResponseDTO create(ExerciseRequestDTO exerciseRequestDTO) {
        return null;
    }

    @Override
    public ExerciseResponseDTO update(ExerciseRequestDTO exerciseRequestDTO) {
        return null;
    }

    @Override
    public List<ExerciseResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public ExerciseResponseDTO findOne(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ExerciseResponseDTO disable(String id) {
        return null;
    }
}
