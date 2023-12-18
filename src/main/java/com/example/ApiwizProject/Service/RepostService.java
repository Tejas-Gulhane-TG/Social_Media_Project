package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.RepostRequestDto;
import org.springframework.http.ResponseEntity;

public interface RepostService {
    ResponseEntity RepostCreate(RepostRequestDto repostRequestDto);
}
