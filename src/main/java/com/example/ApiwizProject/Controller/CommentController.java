package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.CommentRequestDto;
import com.example.ApiwizProject.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/onPost")
    public ResponseEntity CommentOnPost(@RequestBody CommentRequestDto commentRequestDto){
        ResponseEntity responseEntity = commentService.CommentOnPost(commentRequestDto);
        return responseEntity;
    }
}
