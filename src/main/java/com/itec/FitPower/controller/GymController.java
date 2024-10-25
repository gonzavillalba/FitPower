package com.itec.FitPower.controller;

import com.itec.FitPower.dto.request.GymRequestDTO;
import com.itec.FitPower.dto.response.AddClientToNutritionistResponseDTO;
import com.itec.FitPower.dto.response.AddClientToTrainerResponseDTO;
import com.itec.FitPower.dto.response.GymResponseDTO;
import com.itec.FitPower.model.entity.Gym;
import com.itec.FitPower.model.service.GymService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    // Endpoint para crear un nuevo gimnasio
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GymResponseDTO> create(@RequestBody GymRequestDTO gymRequestDto) {
        GymResponseDTO createdGym = gymService.create(gymRequestDto);
        return new ResponseEntity<>(createdGym, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los gimnasios
    @GetMapping
    public ResponseEntity<List<GymResponseDTO>> findAll() {
        List<GymResponseDTO> gyms = gymService.findAll();
        return ResponseEntity.ok(gyms);
    }

    // Endpoint para obtener un gimnasio por código
    @GetMapping("/{name}")
    public ResponseEntity<Gym> findByName(@PathVariable String name) {
        Optional<Gym> gym = gymService.findByName(name);
        return ResponseEntity.ok(gym.get());
    }

    // Endpoint para actualizar un gimnasio existente
    @PutMapping("/{id}")
    public ResponseEntity<GymResponseDTO> update(@RequestBody GymRequestDTO gymRequestDto, @PathVariable Long id) {
        GymResponseDTO updatedGym = gymService.update(gymRequestDto, id);
        return ResponseEntity.ok(updatedGym);
    }

    // Endpoint para eliminar un gimnasio
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        gymService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para agregar un cliente a un gimnasio
    @PutMapping("/add/{name}/clients/{dni}")
    public ResponseEntity<GymResponseDTO> addClientToGym(@PathVariable String name, @PathVariable String dni) {
        GymResponseDTO updatedGym = gymService.addClientToGym(name, dni);
        return ResponseEntity.ok(updatedGym);
    }

    // Endpoint para agregar un entrenador a un gimnasio
    @PostMapping("/add/{name}/trainers/{dni}")
    public ResponseEntity<GymResponseDTO> addTrainerToGym(@PathVariable String name, @PathVariable String dni) {
        GymResponseDTO updatedGym = gymService.addTrainerToGym(name, dni);
        return ResponseEntity.ok(updatedGym);
    }

    // Endpoint para agregar un nutricionista a un gimnasio
    @PostMapping("/add/{name}/nutritionist/{dni}")
    public ResponseEntity<GymResponseDTO> addNutritionistToGym(@PathVariable String name, @PathVariable String dni) {
        GymResponseDTO updatedGym = gymService.addNutritionistToGym(name, dni);
        return ResponseEntity.ok(updatedGym);
    }

    //Endpoint para asignar un entrenador a un cliente
    @PostMapping("/assign/{dniTrainer}/trainer-to-client/{dniClient}")
    public ResponseEntity<AddClientToTrainerResponseDTO>assignTrainerToClient(@PathVariable String dniTrainer, @PathVariable String dniClient){
        AddClientToTrainerResponseDTO addClientToTrainerResponseDto = gymService.assignTrainerToClient(dniTrainer,dniClient);
        return ResponseEntity.ok(addClientToTrainerResponseDto);
    }

    //Endpoint para asignar un entrenador a un cliente
    @PutMapping("/assign/{dniNut}/nutritionist-to-client/{dniClient}")
    public ResponseEntity<AddClientToNutritionistResponseDTO>assignNutritionistToClient(@PathVariable String dniNut, @PathVariable String dniClient){
        AddClientToNutritionistResponseDTO addClientToNutritionistResponseDto = gymService.assignNutritionistToClient(dniNut,dniClient);
        return ResponseEntity.ok(addClientToNutritionistResponseDto);
    }

}
