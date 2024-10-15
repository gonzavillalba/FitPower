package com.itec.FitPower.dto.request;

import com.itec.FitPower.model.entity.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private String goals;
    private ClientStatus clientStatus;
    private String gymName;
}
