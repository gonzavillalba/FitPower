package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.NutritionistRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.dto.response.NutritionistResponseDTO;
import com.itec.FitPower.mapper.ClientMapper;
import com.itec.FitPower.mapper.NutritionistMapper;
import com.itec.FitPower.model.entity.Gym;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.repository.GymRepository;
import com.itec.FitPower.model.repository.NutritionistRepository;
import com.itec.FitPower.model.service.NutritionistService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NutritionistServiceImpl implements NutritionistService {
    private final NutritionistRepository nutritionistRepository;
    private final NutritionistMapper nutritionistMapper;
    private final GymRepository gymRepository;
    private final ClientMapper clientMapper;

    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository, NutritionistMapper nutritionistMapper, GymRepository gymRepository, ClientMapper clientMapper) {
        this.nutritionistRepository = nutritionistRepository;
        this.nutritionistMapper = nutritionistMapper;
        this.gymRepository = gymRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public NutritionistResponseDTO create(NutritionistRequestDTO nutritionistRequestDto) {
        if(nutritionistRepository.findByDni(nutritionistRequestDto.getDni()).isPresent()){
            throw new EntityExistsException("Ya existe un nutricionista con el DNI " + nutritionistRequestDto.getDni());
        }
        Nutritionist nutritionist = nutritionistMapper.dtoToEntity(nutritionistRequestDto);

        if (nutritionistRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(nutritionistRequestDto.getGymName());

            if (gym.isPresent()) {
                nutritionist.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + nutritionistRequestDto.getGymName());
            }
        }
        nutritionist.setActive(true);
        nutritionistRepository.save(nutritionist);
        return nutritionistMapper.entityToDto(nutritionist);
    }

    @Override
    public List<NutritionistResponseDTO> findAll() {
        List<Nutritionist> nutritionists = nutritionistRepository.findAll();
        return nutritionists.stream()
                .map(nutritionistMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Nutritionist getNutritionistByDniOrThrow(String dni) {
        return nutritionistRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El nutricionista con el DNI " + dni + " no existe"));
    }

    @Override
    public NutritionistResponseDTO findByDni(String dni) {
        Nutritionist nutritionist = getNutritionistByDniOrThrow(dni);
        return nutritionistMapper.entityToDto(nutritionist);
    }

    @Override
    public NutritionistResponseDTO update(NutritionistRequestDTO nutritionistRequestDto) {
        Nutritionist existingNutritionist = getNutritionistByDniOrThrow(nutritionistRequestDto.getDni());

        if (nutritionistRequestDto.getName() != null && !nutritionistRequestDto.getName().isEmpty()) {
            existingNutritionist.setName(nutritionistRequestDto.getName());
        }
        if (nutritionistRequestDto.getSurname() != null && !nutritionistRequestDto.getSurname().isEmpty()) {
            existingNutritionist.setSurname(nutritionistRequestDto.getSurname());
        }
        if (nutritionistRequestDto.getPhone() != null && !nutritionistRequestDto.getPhone().isEmpty()) {
            existingNutritionist.setPhone(nutritionistRequestDto.getPhone());
        }
        if (nutritionistRequestDto.getAddress() != null && !nutritionistRequestDto.getAddress().isEmpty()) {
            existingNutritionist.setAddress(nutritionistRequestDto.getAddress());
        }
        if (nutritionistRequestDto.getEmail() != null && !nutritionistRequestDto.getEmail().isEmpty()) {
            existingNutritionist.setEmail(nutritionistRequestDto.getEmail());
        }
        if (nutritionistRequestDto.getProfession() != null && !nutritionistRequestDto.getProfession().isEmpty()) {
            existingNutritionist.setProfession(nutritionistRequestDto.getProfession());
        }
        if (nutritionistRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(nutritionistRequestDto.getGymName());

            if (gym.isPresent()) {
                existingNutritionist.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + nutritionistRequestDto.getGymName());
            }
        }

        existingNutritionist.setActive(nutritionistRequestDto.isActive());

        // Guardamos el entrenador actualizado en la base de datos
        Nutritionist updatedNutritionist = nutritionistRepository.save(existingNutritionist);

        // Devolvemos el DTO actualizado usando el mapper
        return nutritionistMapper.entityToDto(updatedNutritionist);
    }

    @Override
    public void delete(Long id) {
        nutritionistRepository.deleteById(id);
    }

    public List<ClientResponseDTO> getClientsAssociated(String dni){
        Nutritionist nutritionist = getNutritionistByDniOrThrow(dni);
        return nutritionist.getClients()
                .stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public NutritionistResponseDTO disableNutritionistByDni(String dni) {
        Nutritionist nutritionist = nutritionistRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Nutricionista no encontrado con DNI: " + dni));

        nutritionist.setActive(false);
        nutritionistRepository.save(nutritionist);
        return nutritionistMapper.entityToDto(nutritionist);
    }

}
