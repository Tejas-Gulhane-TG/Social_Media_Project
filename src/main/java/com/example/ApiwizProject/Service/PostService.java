package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.PostRequestDto;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity CreatePost(PostRequestDto postRequestDto);
}
