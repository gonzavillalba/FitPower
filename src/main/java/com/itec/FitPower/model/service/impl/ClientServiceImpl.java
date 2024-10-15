package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.mapper.ClientMapper;
import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.repository.ClientRepository;
import com.itec.FitPower.model.service.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private ClientMapper clientMapper;

    @Override
    public ClientResponseDTO create(ClientRequestDTO clientRequestDTO) {
        Client client = clientMapper.dtoToEntity(clientRequestDTO);
        clientRepository.save(client);
        return clientMapper.entityToDto(client);
    }

    @Override
    public ClientResponseDTO update(ClientRequestDTO clientRequestDTO) {
        return null;
    }

    @Override
    public List<ClientResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public ClientResponseDTO findOne(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ClientResponseDTO disable(String id) {
        return null;
    }

}
