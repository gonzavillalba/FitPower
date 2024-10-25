package com.itec.FitPower.mapper;

import com.itec.FitPower.dto.request.SessionRequestDTO;
import com.itec.FitPower.dto.response.SessionResponseDTO;
import com.itec.FitPower.model.entity.Session;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SessionMapper {

    private final ModelMapper modelMapper;


    public SessionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SessionResponseDTO entityToDto(Session session){
        return modelMapper.map(session, SessionResponseDTO.class);
    }

    public Session dtoToEntity(SessionRequestDTO sessionRequestDto){
        return modelMapper.map(sessionRequestDto, Session.class);
    }
}
