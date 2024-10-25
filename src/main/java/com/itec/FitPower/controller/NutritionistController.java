package com.itec.FitPower.controller;

import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDTO;
import com.itec.FitPower.model.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionists")
public class NutritionistController {
    @Autowired
    private NutritionistService nutritionistService;


    @PostMapping
    public ResponseEntity<NutritionistResponseDTO> create(@RequestBody NutritionistRequestDTO nutritionistRequestDto) {
        NutritionistResponseDTO createdNutritionist = nutritionistService.create(nutritionistRequestDto);
        return new ResponseEntity<>(createdNutritionist, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<NutritionistResponseDTO>> findAll() {
        List<NutritionistResponseDTO> nutritionist = nutritionistService.findAll();
        return ResponseEntity.ok(nutritionist);
    }


    @GetMapping("/{dni}")
    public ResponseEntity<NutritionistResponseDTO> findById(@PathVariable String dni) {
        NutritionistResponseDTO nutritionist = nutritionistService.findByDni(dni);
        return ResponseEntity.ok(nutritionist);
    }


    @PutMapping("/{dni}")
    public ResponseEntity<NutritionistResponseDTO> update(@RequestBody NutritionistRequestDTO nutritionistRequestDto) {
        NutritionistResponseDTO updatedNutritionist = nutritionistService.update(nutritionistRequestDto);
        return ResponseEntity.ok(updatedNutritionist);
    }

    @PutMapping("/disable/{dni}")
    public ResponseEntity<NutritionistResponseDTO> disableByDni(@PathVariable String dni) {
        NutritionistResponseDTO nutritionistRequestDto = nutritionistService.disableNutritionistByDni(dni);
        return ResponseEntity.ok(nutritionistRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        nutritionistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/clients/{dni}")
    public ResponseEntity<List<ClientResponseDTO>> getClientsAssociated(@PathVariable String dni){
        List<ClientResponseDTO>nutritionistClients = nutritionistService.getClientsAssociated(dni);
        return ResponseEntity.ok(nutritionistClients);
    }

}
