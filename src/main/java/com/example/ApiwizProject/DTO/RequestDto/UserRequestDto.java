package com.example.ApiwizProject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {

    String name;

    String password;

    String profilePictureUrl;

    String emailId;

    long mobileNo;

    long userName;
}