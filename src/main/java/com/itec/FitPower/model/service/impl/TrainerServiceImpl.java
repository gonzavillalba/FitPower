package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.RoutineRequestDTO;
import com.itec.FitPower.dto.request.TrainerRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.RoutineResponseDTO;
import com.itec.FitPower.dto.response.TrainerResponseDTO;
import com.itec.FitPower.mapper.ClientMapper;
import com.itec.FitPower.mapper.TrainerMapper;
import com.itec.FitPower.model.entity.Gym;
import com.itec.FitPower.model.entity.Trainer;
import com.itec.FitPower.model.repository.GymRepository;
import com.itec.FitPower.model.repository.TrainerRepository;
import com.itec.FitPower.model.service.TrainerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final ClientMapper clientMapper;

    private final GymRepository gymRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository, TrainerMapper trainerMapper, ClientMapper clientMapper, GymRepository gymRepository) {
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
        this.clientMapper = clientMapper;
        this.gymRepository = gymRepository;
    }

    @Override
    public TrainerResponseDTO create(TrainerRequestDTO trainerRequestDto) {
        if(trainerRepository.findByDni(trainerRequestDto.getDni()).isPresent()){
            throw new EntityExistsException("Ya existe un entrenador con el DNI " + trainerRequestDto.getDni());
        }

        Trainer trainer = trainerMapper.dtoToEntity(trainerRequestDto);

        if (trainerRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(trainerRequestDto.getGymName());

            if (gym.isPresent()) {
                trainer.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + trainerRequestDto.getGymName());
            }
        }

        trainerRepository.save(trainer);
        return trainerMapper.entityToDto(trainer);
    }

    @Override
    public List<TrainerResponseDTO> findAll() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainerResponseDTO findOne(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public TrainerResponseDTO disable(String id) {
        return null;
    }

    public Trainer getTrainerByDniOrThrow(String dni) {
        return trainerRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El entrenador con el DNI " + dni + " no existe"));
    }

    @Override
    public TrainerResponseDTO findByDni(String dni) {
        Trainer trainer = getTrainerByDniOrThrow(dni);
        return trainerMapper.entityToDto(trainer);
    }

    @Override
    public TrainerResponseDTO update(TrainerRequestDTO trainerRequestDto) {
        Trainer existingTrainer = getTrainerByDniOrThrow(trainerRequestDto.getDni());

        if (trainerRequestDto.getName() != null && !trainerRequestDto.getName().isEmpty()) {
            existingTrainer.setName(trainerRequestDto.getName());
        }
        if (trainerRequestDto.getSurname() != null && !trainerRequestDto.getSurname().isEmpty()) {
            existingTrainer.setSurname(trainerRequestDto.getSurname());
        }
        if (trainerRequestDto.getPhone() != null && !trainerRequestDto.getPhone().isEmpty()) {
            existingTrainer.setPhone(trainerRequestDto.getPhone());
        }
        if (trainerRequestDto.getAddress() != null && !trainerRequestDto.getAddress().isEmpty()) {
            existingTrainer.setAddress(trainerRequestDto.getAddress());
        }
        if (trainerRequestDto.getEmail() != null && !trainerRequestDto.getEmail().isEmpty()) {
            existingTrainer.setEmail(trainerRequestDto.getEmail());
        }
        if (trainerRequestDto.getProfession() != null && !trainerRequestDto.getProfession().isEmpty()) {
            existingTrainer.setProfession(trainerRequestDto.getProfession());
        }

        if (trainerRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(trainerRequestDto.getGymName());

            if (gym.isPresent()) {
                existingTrainer.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + trainerRequestDto.getGymName());
            }
        }

        existingTrainer.setActive(trainerRequestDto.isActive());

        // Guardamos el entrenador actualizado en la base de datos
        Trainer updatedTrainer = trainerRepository.save(existingTrainer);

        // Devolvemos el DTO actualizado usando el mapper
        return trainerMapper.entityToDto(updatedTrainer);
    }

    @Override
    public void deleteByDni(String dni) {
        Trainer trainer = getTrainerByDniOrThrow(dni);
        trainerRepository.delete(trainer);
    }

    public List<ClientResponseDTO> getClientsAssociated(String dni){
        Trainer trainer = getTrainerByDniOrThrow(dni);
        return trainer.getClients()
                .stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoutineResponseDTO createRoutine(RoutineRequestDTO routineRequestDto) {
        return null;
    }
//    public RoutineResponseDto createRoutine(RoutineRequestDto routineRequestDto){
//        return  routineServiceImpl.create(routineRequestDto);
//    }
}
