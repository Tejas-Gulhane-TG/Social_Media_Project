package com.example.ApiwizProject.DTO.RequestDto;

import com.example.ApiwizProject.Enum.FriendshipStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequestDto {

    long receiverUserName;

    long username;

    String password;

    int friendRequestId;

    @Enumerated(EnumType.STRING)
    FriendshipStatus friendshipStatus;


}
