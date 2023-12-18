package com.example.ApiwizProject.Transformer;

import com.example.ApiwizProject.DTO.RequestDto.FriendRequestDto;
import com.example.ApiwizProject.Enum.FriendshipStatus;
import com.example.ApiwizProject.Model.Friendship;

public class FriendshipTransformer {

    public static Friendship DtoToFriendshipTransformer(FriendRequestDto friendRequestDto){
        return Friendship.builder()
                .status(FriendshipStatus.PENDING)
                .build();
    }
}
