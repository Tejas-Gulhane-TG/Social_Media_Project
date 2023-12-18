package com.example.ApiwizProject.Transformer;


import com.example.ApiwizProject.DTO.RequestDto.UserRequestDto;
import com.example.ApiwizProject.DTO.ResponseDto.UserResponseDto;
import com.example.ApiwizProject.Model.User;

public class UserTransformer {
    public static User RequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .emailId(userRequestDto.getEmailId())
                .mobileNumber(userRequestDto.getMobileNo())
                .profilePictureUrl(userRequestDto.getProfilePictureUrl())
                .build();
    }

    public static UserResponseDto UserToDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .UserName(user.getMobileNumber())
                .build();
    }
}
