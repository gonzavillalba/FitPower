package com.itec.FitPower.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddClientToTrainerResponseDTO {
    private String trainerDni;
    private String clientDni;
    private LocalDateTime registrationDate;
}
