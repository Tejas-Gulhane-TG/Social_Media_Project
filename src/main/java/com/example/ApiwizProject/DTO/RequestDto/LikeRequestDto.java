package com.example.ApiwizProject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeRequestDto {

    long username;

    String password;

    int postId;
}
