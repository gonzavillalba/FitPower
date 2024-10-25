package com.itec.FitPower.mapper;


import com.itec.FitPower.dto.request.TrainerRequestDTO;
import com.itec.FitPower.dto.response.TrainerResponseDTO;
import com.itec.FitPower.model.entity.Trainer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TrainerMapper {
    private final ModelMapper modelMapper;


    public TrainerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TrainerResponseDTO entityToDto(Trainer trainer){
        return modelMapper.map(trainer, TrainerResponseDTO.class);
    }

    public Trainer dtoToEntity(TrainerRequestDTO trainerRequestDto){
        return modelMapper.map(trainerRequestDto, Trainer.class);
    }
}
