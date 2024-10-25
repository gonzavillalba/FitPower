package com.itec.FitPower.model.service.impl;


import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.mapper.ClientMapper;
import com.itec.FitPower.mapper.GymMapper;
import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Gym;
import com.itec.FitPower.model.repository.ClientRepository;
import com.itec.FitPower.model.repository.GymRepository;
import com.itec.FitPower.model.service.ClientService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final GymRepository gymRepository;
    private final ClientMapper clientMapper;
    private final GymMapper gymMapper;


    public ClientServiceImpl(ClientRepository clientRepository, GymRepository gymRepository, ClientMapper clientMapper, GymMapper gymMapper) {
        this.clientRepository = clientRepository;
        this.gymRepository = gymRepository;
        this.clientMapper = clientMapper;
        this.gymMapper = gymMapper;
    }

    @Override
    public ClientResponseDTO create(ClientRequestDTO clientRequestDto) {
        if(clientRepository.findByDni(clientRequestDto.getDni()).isPresent()){
            throw new EntityExistsException("Ya existe un cliente con el dni " + clientRequestDto.getDni());
        }
        if(clientRepository.findByPhone(clientRequestDto.getPhone()).isPresent()){
            throw new EntityExistsException("Ya existe un cliente con el número de teléfono " + clientRequestDto.getPhone());
        }
        if(clientRepository.findByEmail(clientRequestDto.getEmail()).isPresent()){
            throw new EntityExistsException("Ya existe un cliente con el email " + clientRequestDto.getEmail());
        }

        Client client = clientMapper.dtoToEntity(clientRequestDto);
        if (clientRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(clientRequestDto.getGymName());

            if (gym.isPresent()) {
                client.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + clientRequestDto.getGymName());
            }
        }
        client.setActive(true);
        clientRepository.save(client);
        return clientMapper.entityToDto(client);
    }

    @Override
    public List<ClientResponseDTO> findAll() {
        List<Client>clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Client getClientByDniOrThrow(String dni){
        return clientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityExistsException("El cliente con el dni " + dni + " no existe"));
    }
    @Override
    public ClientResponseDTO findByDni(String dni) {
        Client client = getClientByDniOrThrow(dni);
        ClientResponseDTO clientResponseDto = clientMapper.entityToDto(client);
        clientResponseDto.setGymName(client.getGym().getName());
        return clientResponseDto;
    }

    @Override
    public ClientResponseDTO update(ClientRequestDTO clientRequestDto, Long id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con el ID: " + id));

        // Actualizar campos del cliente si no son nulos
        if (clientRequestDto.getName() != null) {
            existingClient.setName(clientRequestDto.getName());
        }
        if (clientRequestDto.getLastname() != null) {
            existingClient.setSurname(clientRequestDto.getLastname());
        }
        if (clientRequestDto.getDni() != null) {
            existingClient.setDni(clientRequestDto.getDni());
        }
        if (clientRequestDto.getPhone() != null) {
            existingClient.setPhone(clientRequestDto.getPhone());
        }
        if (clientRequestDto.getAddress() != null) {
            existingClient.setAddress(clientRequestDto.getAddress());
        }
        if (clientRequestDto.getEmail() != null) {
            existingClient.setEmail(clientRequestDto.getEmail());
        }
        existingClient.setActive(clientRequestDto.isActive());

        if (clientRequestDto.getGoal() != null) {
            existingClient.setGoal(clientRequestDto.getGoal());
        }

        // Solo buscar y actualizar el gimnasio si gymName no es nulo
        if (clientRequestDto.getGymName() != null) {
            Optional<Gym> gym = gymRepository.findByName(clientRequestDto.getGymName());

            if (gym.isPresent()) {
                existingClient.setGym(gym.get());
            } else {
                throw new EntityNotFoundException("Gimnasio no encontrado con el nombre: " + clientRequestDto.getGymName());
            }
        }

        Client updatedClient = clientRepository.save(existingClient);

        return clientMapper.entityToDto(updatedClient); // Cambié esta línea para usar la variable clientResponse
    }


    @Override
    public void delete(String id) {
        Client client = getClientByDniOrThrow(id);
        clientRepository.delete(client);
    }

   /* public List<RoutineResponseDto> getRoutinesByClientDni(String dni) {
        // Obtener el cliente por DNI o lanzar excepción si no existe
        Client client = getClientByDniOrThrow(dni);

        // Obtener la lista de rutinas del cliente
        List<Routine> routines = client.getRoutines();

        // Convertir las rutinas a DTO utilizando un mapper si es necesario
        return routines.stream()
                .map(routineMapper::entityToDto)
                .collect(Collectors.toList());
    }*/

    public ClientResponseDTO disableClientByDni(String dni) {
        Client client = clientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con DNI: " + dni));
        client.setActive(false);
        clientRepository.save(client);

        return clientMapper.entityToDto(client);
    }

}
