package com.example.ApiwizProject.DTO.RequestDto;

import com.example.ApiwizProject.Enum.PostPrivacy;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepostRequestDto {

    int postId;

    String contain;

    @Enumerated(EnumType.STRING)
    PostPrivacy postPrivacy;

    long username;

    String password;

}
