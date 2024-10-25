package com.itec.FitPower.controller;


import com.itec.FitPower.dto.request.ClientRequestDTO;
import com.itec.FitPower.dto.response.ClientResponseDTO;
import com.itec.FitPower.model.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Validated @RequestBody ClientRequestDTO clientRequestDto) {
        ClientResponseDTO createdClient = clientService.create(clientRequestDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        List<ClientResponseDTO> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/{dni}")
    public ResponseEntity<ClientResponseDTO> findByDni(@PathVariable String dni) {
        ClientResponseDTO client = clientService.findByDni(dni);
        return ResponseEntity.ok(client);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@Valid @RequestBody ClientRequestDTO clientRequestDto, @PathVariable Long id) {
        ClientResponseDTO updatedClient = clientService.update(clientRequestDto, id);
        return ResponseEntity.ok(updatedClient);    }

    @PutMapping("/disable/{dni}")
    public ResponseEntity<Void> disable(@PathVariable String dni) {
       clientService.disableClientByDni(dni);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable String dni) {
        clientService.delete(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    }
}
