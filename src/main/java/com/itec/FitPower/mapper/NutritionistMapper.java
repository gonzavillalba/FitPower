package com.itec.FitPower.mapper;


import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDTO;
import com.itec.FitPower.model.entity.Nutritionist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class NutritionistMapper {
    private final ModelMapper modelMapper;


    public NutritionistMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NutritionistResponseDTO entityToDto(Nutritionist nutritionist){
        return modelMapper.map(nutritionist, NutritionistResponseDTO.class);
    }

    public Nutritionist dtoToEntity(NutritionistRequestDTO nutritionistRequestDto){
        return modelMapper.map(nutritionistRequestDto, Nutritionist.class);
    }
}
