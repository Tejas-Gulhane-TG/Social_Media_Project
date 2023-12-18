package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.LikeRequestDto;
import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Model.FriendList;
import com.example.ApiwizProject.Model.Likes;
import com.example.ApiwizProject.Model.Post;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.LikeRepository;
import com.example.ApiwizProject.Repository.PostRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public ResponseEntity LikePost(LikeRequestDto likeRequestDto) {
        User user = userRepository.findByMobileNumber(likeRequestDto.getUsername());
        if(user != null){
            if(Config.matches(likeRequestDto.getPassword(), user.getPassword())){
                Post post = postRepository.findById(likeRequestDto.getPostId());
                if(post != null){
                    if(post.getPostPrivacy() == PostPrivacy.PRIVATE){
                        User poster = post.getUser();
                        List<FriendList> friendLists = poster.getFriendLists();
                        for(int i=0; i<friendLists.size(); i++){
                            FriendList friendList = friendLists.get(i);
                            User user2 = friendList.getUser1();
                            if(user2.getId() == user.getId()){
                                Likes likes = Likes.builder()
                                        .post(post)
                                        .user(user)
                                        .postType(post.getPostType())
                                        .build();
                                likeRepository.save(likes);
                                return new ResponseEntity<>("Post Like Successfully", HttpStatus.OK);
                            }
                        }
                        return new ResponseEntity<>("Post is private only friend can like post ", HttpStatus.NOT_ACCEPTABLE);
                    }
                    Likes likes = Likes.builder()
                            .post(post)
                            .user(user)
                            .postType(post.getPostType())
                            .build();
                    likeRepository.save(likes);
                    return new ResponseEntity<>("Post Like Successfully", HttpStatus.OK);

                }
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
