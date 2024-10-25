package com.itec.FitPower.model.service;



import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDTO;
import com.itec.FitPower.model.entity.Nutritionist;

import java.util.List;

public interface NutritionistService {
    NutritionistResponseDTO create(NutritionistRequestDTO nutritionistRequestDto);
    List<NutritionistResponseDTO> findAll();

    Nutritionist getNutritionistByDniOrThrow(String dni);

    NutritionistResponseDTO findByDni(String dni);

    NutritionistResponseDTO update(NutritionistRequestDTO nutritionistRequestDto);

   void delete(Long id);
    List<ClientResponseDTO> getClientsAssociated(String dni);

    NutritionistResponseDTO disableNutritionistByDni(String dni);

}
