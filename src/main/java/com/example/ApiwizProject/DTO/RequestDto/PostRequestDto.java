package com.example.ApiwizProject.DTO.RequestDto;

import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Enum.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDto {

    long username;

    String password;

    String contain;

    @Enumerated(EnumType.STRING)
    PostType postType;

    @Enumerated(EnumType.STRING)
    PostPrivacy postPrivacy;


}
