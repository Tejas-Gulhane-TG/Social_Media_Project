package com.example.ApiwizProject.Controller;


import com.example.ApiwizProject.DTO.RequestDto.UserRequestDto;
import com.example.ApiwizProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createAccount")
    public ResponseEntity CreateAccount(@RequestBody UserRequestDto userRequestDto){
        ResponseEntity responseEntity = userService.CreateAccount(userRequestDto);
        return responseEntity;
    }

    @PostMapping("/updateProfile")
    public ResponseEntity UpdateProfile(@RequestBody UserRequestDto userRequestDto){
        ResponseEntity responseEntity = userService.UpdateProfile(userRequestDto);
        return responseEntity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity DeleteUser(@RequestBody UserRequestDto userRequestDto){
        ResponseEntity responseEntity = userService.DeleteUser(userRequestDto);
        return responseEntity;
    }

}
