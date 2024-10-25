package com.itec.FitPower.mapper;


import com.itec.FitPower.dto.request.ExerciseRequestDTO;
import com.itec.FitPower.dto.response.ExerciseResponseDTO;
import com.itec.FitPower.model.entity.Exercise;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseMapper {

    private final ModelMapper modelMapper;


    public ExerciseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ExerciseResponseDTO entityToDto(Exercise exercise){
        return modelMapper.map(exercise, ExerciseResponseDTO.class);
    }

    public Exercise dtoToEntity(ExerciseRequestDTO exerciseRequestDto){
        return modelMapper.map(exerciseRequestDto, Exercise.class);
    }
}
