package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.RepostRequestDto;
import com.example.ApiwizProject.Service.RepostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repost")
public class RepostController {

    @Autowired
    RepostService repostService;

    @PostMapping("/create")
    public ResponseEntity RepostCreate(@RequestBody RepostRequestDto repostRequestDto){
        ResponseEntity responseEntity = repostService.RepostCreate(repostRequestDto);
        return responseEntity;
    }
}
