package com.itec.FitPower.mapper;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.model.entity.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    private final ModelMapper modelMapper;


    public ClientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientResponseDTO entityToDto(Client client){
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    public Client dtoToEntity(ClientRequestDTO clientRequestDto){
        return modelMapper.map(clientRequestDto, Client.class);
    }
}
