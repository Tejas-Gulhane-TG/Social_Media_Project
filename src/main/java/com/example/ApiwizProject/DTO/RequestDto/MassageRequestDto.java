package com.example.ApiwizProject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MassageRequestDto {

    long userName;

    String password;

    long receiverId;

    String massage;

}
