package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.MassageRequestDto;
import com.example.ApiwizProject.Model.FriendList;
import com.example.ApiwizProject.Model.Massage;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.FriendshipRepository;
import com.example.ApiwizProject.Repository.MassageRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MassageServiceImpl implements MassageService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MassageRepository massageRepository;
    @Autowired
    FriendshipRepository friendshipRepository;
    @Override
    public ResponseEntity SendMassage(MassageRequestDto massageRequestDto) {
        User sender = userRepository.findByMobileNumber(massageRequestDto.getUserName());
        if(sender != null){
            User receiver = userRepository.findByMobileNumber(massageRequestDto.getReceiverId());
            if(receiver != null){
                if(Config.matches(massageRequestDto.getPassword(), sender.getPassword())){
                    List<FriendList> friendLists = sender.getFriendLists();
                    for(int i=0; i<friendLists.size(); i++){
                        FriendList friendList = friendLists.get(i);
                        User user = friendList.getUser1();
                        if(user.getId() == receiver.getId()){
                            massageRepository.save(
                                    Massage.builder()
                                            .massage(massageRequestDto.getMassage())
                                            .receiver(receiver)
                                            .sender(sender)
                                            .build()
                            );
                            return new ResponseEntity<>(massageRequestDto.getMassage()+"\n Massage Send Successfully to \n"+receiver.getName()+" from "+sender.getName()+" ",HttpStatus.ACCEPTED);
                        }
                    }
                    return new ResponseEntity<>("Request Not Accepted ", HttpStatus.OK);
                }
                return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("Receiver not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("UserName not found", HttpStatus.NOT_FOUND);
    }
}
