package com.itec.FitPower.dto.response;

import com.itec.FitPower.model.entity.ClientStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private String goals;
    private ClientStatus clientStatus;
    private String gymName;
    private LocalDateTime registrationDate;
}
