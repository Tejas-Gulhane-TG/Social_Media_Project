package com.example.ApiwizProject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShareRequestDto {

    long username;

    String password;

    int postId;

    String share_on;
}
