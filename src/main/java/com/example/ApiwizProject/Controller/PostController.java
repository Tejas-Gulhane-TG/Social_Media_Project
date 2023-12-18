package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.PostRequestDto;
import com.example.ApiwizProject.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/new")
    public ResponseEntity CreatePost(@RequestBody PostRequestDto postRequestDto){
        ResponseEntity responseEntity = postService.CreatePost(postRequestDto);
        return responseEntity;
    }
}
