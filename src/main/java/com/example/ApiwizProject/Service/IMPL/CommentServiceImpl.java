package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.CommentRequestDto;
import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Model.*;
import com.example.ApiwizProject.Repository.CommentRepository;
import com.example.ApiwizProject.Repository.PostRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public ResponseEntity CommentOnPost(CommentRequestDto commentRequestDto) {
        User user = userRepository.findByMobileNumber(commentRequestDto.getUsername());
        if(user != null){
            if(Config.matches(commentRequestDto.getPassword(), user.getPassword())){
                Post post = postRepository.findById(commentRequestDto.getPostId());
                if(post != null){
                    if(post.getPostPrivacy() == PostPrivacy.PRIVATE){
                        User user1 = post.getUser();
                        List<FriendList> friendLists = user1.getFriendLists();
                        for(int i=0; i<friendLists.size(); i++){
                            FriendList friendList = friendLists.get(i);
                            User user2 = friendList.getUser1();
                            if(user2.getMobileNumber() == commentRequestDto.getUsername()){
                                Comments comments = Comments.builder()
                                        .comment(commentRequestDto.getComment())
                                        .post(post)
                                        .user(user)
                                        .postType(post.getPostType())
                                        .build();
                                commentRepository.save(comments);
                                return new ResponseEntity<>("Comment on post Successfully", HttpStatus.OK);
                            }
                        }
                        return new ResponseEntity<>("Post is private only friend can Comment on post ", HttpStatus.NOT_ACCEPTABLE);
                    }
                    Comments comments = Comments.builder()
                            .comment(commentRequestDto.getComment())
                            .post(post)
                            .user(user)
                            .postType(post.getPostType())
                            .build();
                    commentRepository.save(comments);
                    return new ResponseEntity<>("Comment On Post Successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
