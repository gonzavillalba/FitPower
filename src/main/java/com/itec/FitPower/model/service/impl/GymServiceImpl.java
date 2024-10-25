package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.GymRequestDTO;
import com.itec.FitPower.dto.response.AddClientToNutritionistResponseDTO;
import com.itec.FitPower.dto.response.AddClientToTrainerResponseDTO;
import com.itec.FitPower.dto.response.GymResponseDTO;
import com.itec.FitPower.mapper.GymMapper;
import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Gym;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.entity.Trainer;
import com.itec.FitPower.model.repository.ClientRepository;
import com.itec.FitPower.model.repository.GymRepository;
import com.itec.FitPower.model.repository.NutritionistRepository;
import com.itec.FitPower.model.repository.TrainerRepository;
import com.itec.FitPower.model.service.GymService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymServiceImpl implements GymService {
    private final GymRepository gymRepository;
    private final GymMapper gymMapper;
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;
    private final NutritionistRepository nutritionistRepository;

    public GymServiceImpl(GymRepository gymRepository, GymMapper gymMapper, ClientRepository clientRepository, TrainerRepository trainerRepository, NutritionistRepository nutritionistRepository) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
        this.clientRepository = clientRepository;
        this.trainerRepository = trainerRepository;
        this.nutritionistRepository = nutritionistRepository;
    }

    @Override
    public GymResponseDTO create(GymRequestDTO gymRequestDto) {
        if (gymRepository.findByName(gymRequestDto.getName()).isPresent()) {
            throw new EntityExistsException("Ya existe un gimnasio con el nombre " + gymRequestDto.getName());
        }
        Gym gym = gymMapper.dtoToEntity(gymRequestDto);
        gymRepository.save(gym);
        return gymMapper.entityToDto(gym);
    }

    @Override
    public List<GymResponseDTO> findAll() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
                .map(gymMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Gym> findByName(String name) {
        Optional<Gym> gym = gymRepository.findByName(name);
        if (gym.isPresent()){
            return gym;
        }
        throw new EntityExistsException("El gimnasio con el nombre " + name + " no existe");
    }

    //Funcion para reutilizar el mensaje de clase con id inexistente
    public Gym getGymByNameOrThrow(String name) {
        return gymRepository.findByName(name)
                .orElseThrow(() -> new EntityExistsException("El gimnasio con el código " + name + " no existe"));
    }

    @Override
    public GymResponseDTO update(GymRequestDTO gymRequestDto, Long id) {
        // Primero obtenemos el Gym existente o lanzamos una excepción si no existe
        Gym existingGym = gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gimnasio no encontrado con el ID: " + id));

        // Actualizamos los campos básicos si no son nulos o vacíos
        if (gymRequestDto.getName() != null && !gymRequestDto.getName().isEmpty()) {
            existingGym.setName(gymRequestDto.getName());
        }
        if (gymRequestDto.getPhone() != null && !gymRequestDto.getPhone().isEmpty()) {
            existingGym.setPhone(gymRequestDto.getPhone());
        }
        if (gymRequestDto.getEmail() != null && !gymRequestDto.getEmail().isEmpty()) {
            existingGym.setEmail(gymRequestDto.getEmail());
        }
        if (gymRequestDto.getAddress() != null && !gymRequestDto.getAddress().isEmpty()) {
            existingGym.setAddress(gymRequestDto.getAddress());
        }

        // Guardamos el gimnasio actualizado en la base de datos
        gymRepository.save(existingGym);

        // Convertimos la entidad actualizada a DTO y la retornamos
        return gymMapper.entityToDto(existingGym);
    }


    @Override
    public void deleteByName(String name) {
        Gym gym = getGymByNameOrThrow(name);
        gymRepository.delete(gym);
    }

    public GymResponseDTO addClientToGym(String name, String dni) {
        // Buscar el gimnasio por el código
        Gym gym = getGymByNameOrThrow(name);
        Client client = clientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El cliente con el DNI "+ dni + "no existe") );

        // Verificar si el cliente ya está en la lista del gimnasio
        if (!gym.getClientList().contains(client)) {
            gym.getClientList().add(client); // Agregar el cliente a la lista del gimnasio
            client.setGym(gym); // Asignar el gimnasio al cliente

            // Guardar los cambios
            gymRepository.save(gym);
            clientRepository.save(client);
        } else {
            throw new EntityExistsException("El cliente ya está asignado a este gimnasio.");
        }

        return gymMapper.entityToDto(gym);
    }

    public GymResponseDTO addTrainerToGym(String name, String dni) {
        // Buscar el gimnasio por el código
        Gym gym = getGymByNameOrThrow(name);
        Trainer trainer = trainerRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El entrenador con el DNI " + dni + " no existe"));

        // Verificar si el entrenador ya existe en la lista
        if (!gym.getTrainerList().contains(trainer)) {
            trainer.setGym(gym); // Asignar el gimnasio al entrenador
            gym.getTrainerList().add(trainer); // Añadir el entrenador a la lista de entrenadores del gimnasio

            // Guardar los cambios en la base de datos
            gymRepository.save(gym);
            trainerRepository.save(trainer);
        } else {
            throw new EntityExistsException("El entrenador ya está asignado a este gimnasio.");
        }

        // Devolver el DTO actualizado
        return gymMapper.entityToDto(gym);
    }


    public GymResponseDTO addNutritionistToGym(String name, String dni) {
        // Buscar el gimnasio por el código
        Gym gym = getGymByNameOrThrow(name);
        Nutritionist nutritionist = nutritionistRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El nutricionista con el DNI " + dni + " no existe"));

        // Verificar si el nutricionista ya existe en la lista
        if (!gym.getNutritionistList().contains(nutritionist)) {
            nutritionist.setGym(gym); // Asignar el gimnasio al nutricionista
            gym.getNutritionistList().add(nutritionist); // Añadir el nutricionista a la lista de nutricionistas del gimnasio

            // Guardar los cambios en la base de datos
            gymRepository.save(gym);
            nutritionistRepository.save(nutritionist);
        } else {
            throw new EntityExistsException("El nutricionista ya está asignado a este gimnasio.");
        }

        // Devolver el DTO actualizado
        return gymMapper.entityToDto(gym);
    }


    public AddClientToTrainerResponseDTO assignTrainerToClient(String dniTrainer, String dniClient){
        // Buscar entrenador y cliente por dni
        Trainer trainer = trainerRepository.findByDni(dniTrainer)
                .orElseThrow(() -> new EntityExistsException("El entrenador con el DNI " + dniTrainer + " no existe"));

        Client client = clientRepository.findByDni(dniClient)
                .orElseThrow(() -> new EntityExistsException("El cliente con el DNI " + dniClient + " no existe"));

        // Verificar que el cliente y el entrenador pertenezcan al mismo gimnasio
        if (client.getGym() == null || !client.getGym().equals(trainer.getGym())) {
            throw new IllegalArgumentException("El entrenador y el cliente deben pertenecer al mismo gimnasio.");
        }

        // Verificar si el cliente ya existe en la lista del entrenador
        if (client.getTrainer() == null || !client.getTrainer().equals(trainer)) {
            client.setTrainer(trainer); // Asignar el entrenador al cliente
            trainer.getClients().add(client); // Añadir el cliente a la lista de clientes del entrenador

            // Guardar los cambios en la base de datos
            clientRepository.save(client);
            return createClientToTrainer(dniTrainer, dniClient);
        } else {
            throw new EntityExistsException("El entrenador ya está asignado a este cliente.");
        }

    }

    public AddClientToNutritionistResponseDTO assignNutritionistToClient(String dniNutritionist, String dniClient){
        // Buscar entrenador y cliente por dni
        Nutritionist nutritionist = nutritionistRepository.findByDni(dniNutritionist)
                .orElseThrow(() -> new EntityExistsException("El nutricionista con el DNI " + dniNutritionist + " no existe"));

        Client client = clientRepository.findByDni(dniClient)
                .orElseThrow(() -> new EntityExistsException("El cliente con el DNI " + dniClient + " no existe"));

        // Verificar que el cliente y el nutricionista pertenezcan al mismo gimnasio
        if (client.getGym() == null || !client.getGym().equals(nutritionist.getGym())) {
            throw new IllegalArgumentException("El nutricionista y el cliente deben pertenecer al mismo gimnasio.");
        }

        // Verificar si el cliente ya existe en la lista del entrenador
        if (client.getNutritionist() == null || !client.getNutritionist().equals(nutritionist)) {
            client.setNutritionist(nutritionist); // Asignar el nutricionista al cliente
            nutritionist.getClients().add(client); // Añadir el cliente a la lista de clientes del nutricionista

            // Guardar los cambios en la base de datos
            clientRepository.save(client);
            return createClientToNutritionist(dniNutritionist, dniClient);
        } else {
            throw new EntityExistsException("El nutricionista ya está asignado a este cliente.");
        }

    }

    public AddClientToNutritionistResponseDTO createClientToNutritionist(String dniNutritionist, String dniClient){
        if (dniNutritionist != null || dniClient != null){
            AddClientToNutritionistResponseDTO clientToNutritionist = new AddClientToNutritionistResponseDTO();
            clientToNutritionist.setClientDni(dniClient);
            clientToNutritionist.setNutritionistDni(dniNutritionist);
            clientToNutritionist.setRegistrationDate(LocalDateTime.now());
            return clientToNutritionist;
        }
        return null;
    }

    public AddClientToTrainerResponseDTO createClientToTrainer(String dniTrainer, String dniClient){
        if (dniTrainer != null || dniClient != null){
            AddClientToTrainerResponseDTO clientToTrainerResponseDto = new AddClientToTrainerResponseDTO();
            clientToTrainerResponseDto.setClientDni(dniClient);
            clientToTrainerResponseDto.setTrainerDni(dniTrainer);
            clientToTrainerResponseDto.setRegistrationDate(LocalDateTime.now());
            return clientToTrainerResponseDto;
        }
        return null;
    }
}
