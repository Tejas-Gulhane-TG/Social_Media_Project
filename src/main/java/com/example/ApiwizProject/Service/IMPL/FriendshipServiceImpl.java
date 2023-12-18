package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.FriendRequestDto;
import com.example.ApiwizProject.Enum.FriendshipStatus;
import com.example.ApiwizProject.Enum.NotificationType;
import com.example.ApiwizProject.Model.FriendList;
import com.example.ApiwizProject.Model.Friendship;
import com.example.ApiwizProject.Model.Notification;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.FriendListRepository;
import com.example.ApiwizProject.Repository.FriendshipRepository;
import com.example.ApiwizProject.Repository.NotificationRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.FriendshipService;
import com.example.ApiwizProject.Transformer.FriendshipTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    FriendshipRepository friendshipRepository;
    @Autowired
    FriendListRepository friendListRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity SendFriendRequest(FriendRequestDto friendRequestDto) {
        User sender = userRepository.findByMobileNumber(friendRequestDto.getUsername());
        if(sender!=null){
            if(Config.matches(friendRequestDto.getPassword(), sender.getPassword())){
                User receiver = userRepository.findByMobileNumber(friendRequestDto.getReceiverUserName());
                if(receiver != null){
                    Friendship friendship = FriendshipTransformer.DtoToFriendshipTransformer(friendRequestDto);
                    friendship.setUser1(sender);
                    friendship.setUser2(receiver);
                    friendshipRepository.save(friendship);
                    notificationRepository.save(
                            Notification.builder()
                                    .notificationType(NotificationType.REQUEST)
                                    .user(receiver)
                                    .build()
                    );
                    return new ResponseEntity<>("Friend request Send from "+sender.getName()+" to "+receiver.getName()+" Successfully Id is "+friendship.getId(), HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>("UserId not Found to send friend request "+friendRequestDto.getReceiverUserName()+" ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("UserName not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity RequestResponse(FriendRequestDto friendRequestDto) {
        User user = userRepository.findByMobileNumber(friendRequestDto.getUsername());
        if(user != null){
            if(Config.matches(friendRequestDto.getPassword(), user.getPassword())){
                Friendship friendship = friendshipRepository.findById(friendRequestDto.getFriendRequestId());
                if(friendship!=null){
                    if(friendship.getStatus() == FriendshipStatus.ACCEPTED){
                        return new ResponseEntity<>("Already Accept ", HttpStatus.NOT_ACCEPTABLE);
                    }
                    if(friendRequestDto.getFriendshipStatus() == FriendshipStatus.ACCEPTED){
                        friendship.setStatus(friendRequestDto.getFriendshipStatus());
                        friendshipRepository.save(friendship);
                        FriendList friendList = FriendList.builder()
                                .user1(friendship.getUser1())
                                .user(user)
                                .build();
                        FriendList friendList1 = FriendList.builder()
                                .user1(user)
                                .user(friendship.getUser1())
                                .build();
                        friendListRepository.save(friendList1);
                        friendListRepository.save(friendList);
                        return new ResponseEntity<>("Request Accepted", HttpStatus.NOT_ACCEPTABLE);
                    }
                    if(friendRequestDto.getFriendshipStatus() == FriendshipStatus.DECLINED){
                        friendship.setStatus(friendRequestDto.getFriendshipStatus());
                        friendshipRepository.save(friendship);
                        return new ResponseEntity<>("Request Declined", HttpStatus.NOT_ACCEPTABLE);
                    }
                    return new ResponseEntity<>("Request pending", HttpStatus.NOT_ACCEPTABLE);
                }
                return new ResponseEntity<>("no request found", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User not Exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity GetAllFriends(FriendRequestDto friendRequestDto) {
        User user = userRepository.findByMobileNumber(friendRequestDto.getUsername());
        if(user != null){
            if(Config.matches(friendRequestDto.getPassword(), user.getPassword())){
                List<FriendList> friendList = user.getFriendLists();
                List<String> FriendName = new ArrayList<>();
                for(int i=0; i<friendList.size(); i++){
                    FriendList friendList1 = friendList.get(i);
                    FriendName.add(friendList1.getUser1().getName());
                }
                return new ResponseEntity<>(FriendName, HttpStatus.FOUND);
            }
            return new ResponseEntity<>("Wrong Password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User not Exist", HttpStatus.NOT_FOUND);
    }

}
