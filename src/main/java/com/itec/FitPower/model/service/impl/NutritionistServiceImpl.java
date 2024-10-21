package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDto;
import com.itec.FitPower.exception.NotFoundException;
import com.itec.FitPower.mapper.NutritionistMapper;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.repository.NutritionistRepository;
import com.itec.FitPower.util.CRUD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionistServiceImpl implements CRUD<NutritionistResponseDto, NutritionistRequestDTO> {

    private final NutritionistRepository nutritionistRepository;
    private final NutritionistMapper nutritionistMapper;

    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository, NutritionistMapper nutritionistMapper) {
        this.nutritionistRepository = nutritionistRepository;
        this.nutritionistMapper = nutritionistMapper;
    }

    @Override
    public NutritionistResponseDto create(NutritionistRequestDTO nutritionistRequestDTO) {
        try {
            Nutritionist nutritionist = nutritionistMapper.convertToEntity(nutritionistRequestDTO);
            Nutritionist nutritionistSaved = nutritionistRepository.save(nutritionist);
            return nutritionistMapper.convertToDto(nutritionistSaved);
        } catch (NotFoundException e) {
            throw new NotFoundException("Error while creating the nutritionist...");
        }
    }

    @Override
    public NutritionistResponseDto update(NutritionistRequestDTO nutritionistRequestDTO) {
        try {
            Nutritionist existingNutritionist = nutritionistRepository.findByDni(nutritionistRequestDTO.getDni())
                    .orElseThrow(() -> new NotFoundException("Nutritionist with DNI " + nutritionistRequestDTO.getDni() + " not found..."));

            existingNutritionist.setName(nutritionistRequestDTO.getName());
            existingNutritionist.setEmail(nutritionistRequestDTO.getEmail());
            existingNutritionist.setAddress(nutritionistRequestDTO.getAddress());
            existingNutritionist.setProfession(nutritionistRequestDTO.getProfession());
            existingNutritionist.setPhone(nutritionistRequestDTO.getPhone());
            existingNutritionist.setGym(nutritionistRequestDTO.getGym());

            Nutritionist updatedNutritionist = nutritionistRepository.save(existingNutritionist);
            return nutritionistMapper.convertToDto(updatedNutritionist);
        } catch (NotFoundException e) {
            throw new NotFoundException("Error while updting the nutritionist...");
        }
    }

    @Override
    public void delete(String id) {
        Nutritionist nutritionist = nutritionistRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Nutritionist not found with current id..."));
        nutritionist.setActive(false);
        nutritionistRepository.save(nutritionist);
    }

    @Override
    public List<NutritionistResponseDto> findAll() {
        return List.of();
    }

    @Override
    public NutritionistResponseDto findOne(String id) {
        return null;
    }

    @Override
    public NutritionistResponseDto disable(String id) {
        return null;
    }
}
