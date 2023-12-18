package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.UserRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity CreateAccount(UserRequestDto userRequestDto);

    ResponseEntity UpdateProfile(UserRequestDto userRequestDto);

    ResponseEntity DeleteUser(UserRequestDto userRequestDto);
}
