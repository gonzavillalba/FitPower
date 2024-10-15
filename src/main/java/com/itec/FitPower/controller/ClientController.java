package com.itec.FitPower.controller;

import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.model.service.ClientService;
import com.itec.FitPower.util.Controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor

public class ClientController implements Controller<ClientResponseDTO, ClientRequestDTO> {
    private final ClientService clientService;

    @Override
    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO createdClient = clientService.create(clientRequestDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<ClientResponseDTO> findById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ClientResponseDTO> update(ClientRequestDTO clientRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        return null;
    }
}
