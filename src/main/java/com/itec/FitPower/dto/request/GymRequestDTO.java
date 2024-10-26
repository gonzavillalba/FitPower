package com.itec.FitPower.dto.request;

import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Nutritionist;
import com.itec.FitPower.model.entity.Trainer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymRequestDTO {
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;
    @NotBlank(message = "El teléfono es obligatorio.")
    private String phone;
    @Email(message = "El correo electrónico debe ser válido")
    private String email;
    private String address;
}