package com.example.ApiwizProject.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDto {

    long username;

    String password;

    int postId;

    String comment;
}
