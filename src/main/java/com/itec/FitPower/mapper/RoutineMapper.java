package com.itec.FitPower.mapper;


import com.itec.FitPower.dto.request.RoutineRequestDTO;
import com.itec.FitPower.dto.response.RoutineResponseDTO;
import com.itec.FitPower.model.entity.Routine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoutineMapper {
    private final ModelMapper modelMapper;


    public RoutineMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoutineResponseDTO entityToDto(Routine routine){
        return modelMapper.map(routine, RoutineResponseDTO.class);
    }

    public Routine dtoToEntity(RoutineRequestDTO routineRequestDto){
        return modelMapper.map(routineRequestDto, Routine.class);
    }
}
