package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.TrainerRequestDto;
import com.itec.FitPower.dto.response.TrainerResponseDto;
import com.itec.FitPower.model.service.TrainerService;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    @Override
    public TrainerResponseDto create(TrainerRequestDto trainerRequestDto) {
        return null;
    }

    @Override
    public TrainerResponseDto update(TrainerRequestDto trainerRequestDto) {
        return null;
    }

    @Override
    public List<TrainerResponseDto> findAll() {
        return List.of();
    }

    @Override
    public TrainerResponseDto findOne(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public TrainerResponseDto disable(String id) {
        return null;
    }
}
