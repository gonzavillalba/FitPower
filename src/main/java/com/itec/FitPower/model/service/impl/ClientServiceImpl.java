package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.Exception.EntityException;
import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.mapper.ClientMapper;
import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.ClientStatus;
import com.itec.FitPower.model.repository.ClientRepository;
import com.itec.FitPower.model.repository.ClientStatusRepository;
import com.itec.FitPower.model.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private ClientStatusRepository clientStatusRepository;
    private ClientMapper clientMapper;

    @Override
    public ClientResponseDTO create(ClientRequestDTO clientRequestDTO) {
    // Crear los estados inicial y actual
    ClientStatus clientStatus = new ClientStatus();
        clientStatus.setWeight(clientRequestDTO.getClientStatus().getWeight());
        clientStatus.setHeight(clientRequestDTO.getClientStatus().getHeight());
        clientStatus.setBodyMass(clientRequestDTO.getClientStatus().getBodyMass());
        clientStatus.setBodyFat(clientRequestDTO.getClientStatus().getBodyFat());

    // Guardar los estados en la base de datos
        clientStatusRepository.save(clientStatus);

    Client client = clientMapper.dtoToEntity(clientRequestDTO);
        client.getClientStatuses().add(clientStatus);
        client.setRegistrationDate(LocalDateTime.now());
        clientRepository.save(client);
        return clientMapper.entityToDto(client);
    }

    @Override
    public ClientResponseDTO update(ClientRequestDTO clientRequestDTO) {
        Client existingClient = getClientByDniOrThrow(clientRequestDTO.getDni());
        if (clientRequestDTO.getName() != null && !clientRequestDTO.getName().isEmpty()) {
            existingClient.setName(clientRequestDTO.getName());
        }
        if (clientRequestDTO.getLastname() != null && !clientRequestDTO.getLastname().isEmpty()) {
            existingClient.setSurname(clientRequestDTO.getLastname());
        }
        if (clientRequestDTO.getPhone() != null && !clientRequestDTO.getPhone().isEmpty()) {
            existingClient.setPhone(clientRequestDTO.getPhone());
        }
        if (clientRequestDTO.getAddress() != null && !clientRequestDTO.getAddress().isEmpty()) {
            existingClient.setAddress(clientRequestDTO.getAddress());
        }
        if (clientRequestDTO.getEmail() != null && !clientRequestDTO.getEmail().isEmpty()) {
            existingClient.setEmail(clientRequestDTO.getEmail());
        }
        if (clientRequestDTO.getGoals() != null && !clientRequestDTO.getGoals().isEmpty()) {
            existingClient.setGoal(clientRequestDTO.getGoals());
        }
        // Guardamos el cliente actualizado en la base de datos
        Client updatedClient = clientRepository.save(existingClient);

        // Devolvemos el DTO actualizado usando el mapper
        return clientMapper.entityToDto(updatedClient);
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
                .orElseThrow(() -> new EntityException("El cliente con el dni " + dni + " no existe"));
    }

    @Override
    public ClientResponseDTO findOne(String id) {
        Client client = getClientByDniOrThrow(id);
        return clientMapper.entityToDto(client);
    }

    @Override
    public void delete(String id) {
        Client client = getClientByDniOrThrow(id);
        clientRepository.delete(client);
    }

    @Override
    public ClientResponseDTO disable(String id) {
        Client client = getClientByDniOrThrow(id);
        client.setActive(false);
        return clientMapper.entityToDto(client);
    }

}
