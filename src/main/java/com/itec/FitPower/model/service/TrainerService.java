package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.TrainerRequestDTO;
import com.itec.FitPower.dto.response.TrainerResponseDto;
import com.itec.FitPower.util.CRUD;

public interface TrainerService extends CRUD<TrainerResponseDto, TrainerRequestDTO> {
}
