package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.CommentRequestDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity CommentOnPost(CommentRequestDto commentRequestDto);
}
