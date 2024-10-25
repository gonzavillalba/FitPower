package com.itec.FitPower.controller;

import com.itec.FitPower.dto.request.RoutineRequestDTO;
import com.itec.FitPower.dto.request.TrainerRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.RoutineResponseDTO;
import com.itec.FitPower.dto.response.TrainerResponseDTO;
import com.itec.FitPower.model.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @PostMapping
    public ResponseEntity<TrainerResponseDTO> create(@Valid @RequestBody TrainerRequestDTO trainerRequestDto) {
        TrainerResponseDTO createdTrainer = trainerService.create(trainerRequestDto);
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<TrainerResponseDTO>> findAll() {
        List<TrainerResponseDTO> trainers = trainerService.findAll();
        return ResponseEntity.ok(trainers);
    }


    @GetMapping("/{dni}")
    public ResponseEntity<TrainerResponseDTO> findByDni(@PathVariable String dni) {
        TrainerResponseDTO trainer = trainerService.findByDni(dni);
        return ResponseEntity.ok(trainer);
    }


    @PutMapping("/{dni}")
    public ResponseEntity<TrainerResponseDTO> update(@Valid @RequestBody TrainerRequestDTO trainerRequestDto) {
        TrainerResponseDTO updatedTrainer = trainerService.update(trainerRequestDto);
        return ResponseEntity.ok(updatedTrainer);
    }


    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteByDni(@PathVariable String dni) {
        trainerService.deleteByDni(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/clients/{dni}")
    public ResponseEntity<List<ClientResponseDTO>> getClients(@PathVariable String dni){
        List<ClientResponseDTO>trainerClients = trainerService.getClientsAssociated(dni);
        return ResponseEntity.ok(trainerClients);
    }

    @PostMapping("/routines")
    public ResponseEntity<RoutineResponseDTO> createRoutineForClient(@RequestBody RoutineRequestDTO routineRequestDto) {
        RoutineResponseDTO routineResponse = trainerService.createRoutine(routineRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(routineResponse);
    }

    //falta crear la funcion de plan nut
    //creamos rutina y agregamos a trainer
}
