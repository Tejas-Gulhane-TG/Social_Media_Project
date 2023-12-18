package com.example.ApiwizProject.Controller;

import com.example.ApiwizProject.DTO.RequestDto.MassageRequestDto;
import com.example.ApiwizProject.Service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/massage")
public class MassageController {

    @Autowired
    MassageService massageService;

    @PostMapping("/send")
    public ResponseEntity SendMassage(@RequestBody MassageRequestDto massageRequestDto){
        ResponseEntity responseEntity = massageService.SendMassage(massageRequestDto);
        return responseEntity;
    }
}
