package com.itec.FitPower.controller;

import com.itec.FitPower.dto.request.ExerciseRequestDTO;
import com.itec.FitPower.dto.response.ExerciseResponseDTO;
import com.itec.FitPower.model.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ExerciseResponseDTO> create(@RequestBody ExerciseRequestDTO exerciseRequestDto) {
        ExerciseResponseDTO createdExercise = exerciseService.create(exerciseRequestDto);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> findAll() {
        List<ExerciseResponseDTO> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }


  /*  @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> findById(@PathVariable Long id) {
        ExerciseResponseDTO exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ExerciseResponseDTO> findByName(@PathVariable String name) {
        Optional<ExerciseResponseDTO> exercise = exerciseService.findByName(name);
        return exercise.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/



    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> update(@RequestBody ExerciseRequestDTO exerciseRequestDto) {
        ExerciseResponseDTO updatedExercise = exerciseService.update(exerciseRequestDto);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.delete(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
