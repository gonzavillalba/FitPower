package com.itec.FitPower.model.service;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.util.CRUD;

public interface ClientService extends CRUD<ClientResponseDTO, ClientRequestDTO> {
}
