package com.itec.FitPower.model.service.impl;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.model.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public ClientResponseDTO create(ClientRequestDTO clientRequestDTO) {
        return null;
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
