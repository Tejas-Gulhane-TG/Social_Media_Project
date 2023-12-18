package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.MassageRequestDto;
import org.springframework.http.ResponseEntity;

public interface MassageService {
    ResponseEntity SendMassage(MassageRequestDto massageRequestDto);
}
