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
    private String phone;
    private String address;
    private String email;
    private boolean active;
    private List<ClientStatus> statuses;
    private String goal;
    private String gymName;
}
