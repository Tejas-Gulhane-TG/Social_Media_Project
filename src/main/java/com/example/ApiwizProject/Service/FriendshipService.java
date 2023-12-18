package com.example.ApiwizProject.Service;

import com.example.ApiwizProject.DTO.RequestDto.FriendRequestDto;
import org.springframework.http.ResponseEntity;

public interface FriendshipService {
    ResponseEntity SendFriendRequest(FriendRequestDto friendRequestDto);

    ResponseEntity RequestResponse(FriendRequestDto friendRequestDto);

    ResponseEntity GetAllFriends(FriendRequestDto friendRequestDto);
}
