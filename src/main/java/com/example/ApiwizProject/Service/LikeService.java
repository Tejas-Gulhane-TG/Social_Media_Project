package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.LikeRequestDto;
import org.springframework.http.ResponseEntity;

public interface LikeService {
    ResponseEntity LikePost(LikeRequestDto likeRequestDto);
}
