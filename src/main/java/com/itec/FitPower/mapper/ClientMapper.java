package com.itec.FitPower.mapper;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.model.entity.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMapper {
    private final ModelMapper modelMapper;

    public ClientResponseDTO toDto(Client client){
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    public Client toEntity(ClientRequestDTO clientRequestDTO){
        return modelMapper.map(clientRequestDTO, Client.class);
    }

    public ClientResponseDTO entityToDto(Client client) {
        ClientResponseDTO clientResponseDto = new ClientResponseDTO();
        clientResponseDto.setId((client.getId()));
        clientResponseDto.setDni(client.getDni());
        clientResponseDto.setName(client.getName());
        clientResponseDto.setLastname(client.getSurname());
        clientResponseDto.setAddress(client.getAddress());
        clientResponseDto.setPhone(client.getPhone());
        clientResponseDto.setEmail(client.getEmail());
        clientResponseDto.setClientStatus(client.getInitState());
        return clientResponseDto;
    }

    public Client dtoToEntity(ClientRequestDTO dtoRequest) {
        Client client = new Client();
        client.setDni(dtoRequest.getDni());
        client.setName(dtoRequest.getName());
        client.setSurname(dtoRequest.getLastname());
        client.setAddress(dtoRequest.getAddress());
        client.setPhone(dtoRequest.getPhone());
        client.setEmail(dtoRequest.getEmail());
        client.setInitState(dtoRequest.getClientStatus());
        return client;
    }

}
