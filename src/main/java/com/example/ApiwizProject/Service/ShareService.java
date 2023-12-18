package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.ShareRequestDto;
import org.springframework.http.ResponseEntity;

public interface ShareService {
    ResponseEntity SharePost(ShareRequestDto shareRequestDto);
}
