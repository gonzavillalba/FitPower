package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.util.CRUD;

import java.util.List;

public interface ClientService extends CRUD<ClientResponseDTO, ClientRequestDTO> {
    ClientResponseDto create(ClientRequestDto clientRequestDto);

    List<ClientResponseDto> findAll();

    ClientResponseDto findByDni(String dni);

    ClientResponseDto update(ClientRequestDto clientRequestDto, Long id);

    void delete(String id);


    ClientResponseDto disableClientByDni(String dni);

}
