package com.itec.FitPower.dto.response;

import com.itec.FitPower.model.entity.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private String goals;
    private List<ClientStatus> clientStatuses;
    private String gymName;
    private LocalDateTime registrationDate;
}
