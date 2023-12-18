package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.PostRequestDto;
import com.example.ApiwizProject.Model.Post;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.PostRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity CreatePost(PostRequestDto postRequestDto) {
        User user = userRepository.findByMobileNumber(postRequestDto.getUsername());
        if(user != null){
            if(Config.matches(postRequestDto.getPassword(), user.getPassword())){
                        Post post = Post.builder()
                                .postType(postRequestDto.getPostType())
                                .postPrivacy(postRequestDto.getPostPrivacy())
                                .contain(postRequestDto.getContain())
                                .user(user)
                                .build();
                        postRepository.save(post);
                return new ResponseEntity<>(postRequestDto.getContain()+"\n Post Id is "+post.getId()+"\nPosted Success by \n"+user.getName(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
