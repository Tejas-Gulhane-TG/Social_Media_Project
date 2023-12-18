package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.UserRequestDto;
import com.example.ApiwizProject.DTO.ResponseDto.UserResponseDto;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.FriendshipRepository;
import com.example.ApiwizProject.Repository.MassageRepository;
import com.example.ApiwizProject.Repository.NotificationRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.UserService;
import com.example.ApiwizProject.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    MassageRepository massageRepository;
    @Autowired
    FriendshipRepository friendshipRepository;
    @Override
    public ResponseEntity CreateAccount(UserRequestDto userRequestDto) {
        User user = userRepository.findByMobileNumber(userRequestDto.getMobileNo());
        if(user != null){
            return new ResponseEntity<>("User mobile No Already Exist", HttpStatus.NOT_FOUND);
        }
        User user1 = UserTransformer.RequestDtoToUser(userRequestDto);
        user1.setPassword(Config.encode(userRequestDto.getPassword()));
        userRepository.save(user1);
        UserResponseDto userResponseDto = UserTransformer.UserToDto(user1);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity UpdateProfile(UserRequestDto userRequestDto) {
        User user = userRepository.findByMobileNumber(userRequestDto.getMobileNo());
        if(user != null){

            if(Config.matches(userRequestDto.getPassword(), user.getPassword())){
                user.setName(userRequestDto.getName());
                user.setProfilePictureUrl(userRequestDto.getProfilePictureUrl());
                user.setEmailId(userRequestDto.getEmailId());
                userRepository.save(user);
                return new ResponseEntity<>("User update successfully ", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Wrong password  ", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User not Exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity DeleteUser(UserRequestDto userRequestDto) {
        User user = userRepository.findByMobileNumber(userRequestDto.getUserName());
        if(user != null){
            if(Config.matches(userRequestDto.getPassword(), user.getPassword())){
                userRepository.delete(user);
                return new ResponseEntity<>("User Delete Successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong Password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("UserName not found", HttpStatus.NOT_FOUND);
    }


}
