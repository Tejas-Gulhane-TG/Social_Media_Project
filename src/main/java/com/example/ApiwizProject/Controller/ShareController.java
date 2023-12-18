package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.ShareRequestDto;
import com.example.ApiwizProject.Service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/share")
public class ShareController {

    @Autowired
    ShareService shareService;

    @PostMapping("/post")
    public ResponseEntity SharePost(@RequestBody ShareRequestDto shareRequestDto){
        ResponseEntity responseEntity = shareService.SharePost(shareRequestDto);
        return responseEntity;
    }
}
