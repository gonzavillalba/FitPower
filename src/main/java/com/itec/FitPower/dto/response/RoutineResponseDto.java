package com.itec.FitPower.dto.response;

import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Session;
import com.itec.FitPower.model.entity.Trainer;
import com.itec.FitPower.model.entity.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RoutineResponseDto {
    private Client client;
    private Trainer trainer;
    private String routineCode;
    private String routineType;
    private LocalDate creationDate;
    private  LocalDate startDate;
    private boolean active;
    private List<Session> sessions;
    private Status status;
}
