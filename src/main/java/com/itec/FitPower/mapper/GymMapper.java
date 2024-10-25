package com.itec.FitPower.mapper;


import com.itec.FitPower.dto.request.GymRequestDTO;
import com.itec.FitPower.dto.response.GymResponseDTO;
import com.itec.FitPower.model.entity.Gym;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GymMapper {

    private final ModelMapper modelMapper;


    public GymMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GymResponseDTO entityToDto(Gym gym){
        return modelMapper.map(gym, GymResponseDTO.class);
    }

    public Gym dtoToEntity(GymRequestDTO gymRequestDto){
        return modelMapper.map(gymRequestDto, Gym.class);
    }
}
