package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.FriendRequestDto;
import com.example.ApiwizProject.Service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friend")
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;
    @PostMapping("/sendFriendRequest")
    public ResponseEntity SendFriendRequest(@RequestBody FriendRequestDto friendRequestDto){
        ResponseEntity responseEntity = friendshipService.SendFriendRequest(friendRequestDto);
        return responseEntity;
    }

    @PostMapping("/requestResponse")
    public ResponseEntity RequestResponse(@RequestBody FriendRequestDto friendRequestDto){
        ResponseEntity responseEntity = friendshipService.RequestResponse(friendRequestDto);
        return responseEntity;
    }

    @GetMapping("/getAllFriends")
    public ResponseEntity GetAllFriends(@RequestBody FriendRequestDto friendRequestDto){
        ResponseEntity responseEntity = friendshipService.GetAllFriends(friendRequestDto);
        return responseEntity;
    }

}
