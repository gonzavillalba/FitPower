package com.itec.FitPower.mapper;

import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDto;
import com.itec.FitPower.model.entity.Nutritionist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class NutritionistMapper {

    private final ModelMapper modelMapper;

    public NutritionistMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NutritionistResponseDto convertToDto(Nutritionist nutritionist) {
        return modelMapper.map(nutritionist, NutritionistResponseDto.class);
    }

    public Nutritionist convertToEntity(NutritionistRequestDTO nutritionistRequestDTO) {
        return modelMapper.map(nutritionistRequestDTO, Nutritionist.class);
    }
}
