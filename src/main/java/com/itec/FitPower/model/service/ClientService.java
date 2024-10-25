package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.util.CRUD;

import java.util.List;

public interface ClientService{
    ClientResponseDTO create(ClientRequestDTO clientRequestDto);

    List<ClientResponseDTO> findAll();

    ClientResponseDTO findByDni(String dni);

    ClientResponseDTO update(ClientRequestDTO clientRequestDto, Long id);

    void delete(String id);


    ClientResponseDTO disableClientByDni(String dni);

}
