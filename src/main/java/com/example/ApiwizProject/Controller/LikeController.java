package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.LikeRequestDto;
import com.example.ApiwizProject.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping("/post")
    public ResponseEntity LikePost(@RequestBody LikeRequestDto likeRequestDto){
        ResponseEntity responseEntity = likeService.LikePost(likeRequestDto);
        return responseEntity;
    }
}
