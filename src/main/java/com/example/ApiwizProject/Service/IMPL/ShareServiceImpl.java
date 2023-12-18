package com.example.ApiwizProject.Service.IMPL;

import com.example.ApiwizProject.DTO.RequestDto.ShareRequestDto;
import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Model.Post;
import com.example.ApiwizProject.Model.Share;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.PostRepository;
import com.example.ApiwizProject.Repository.ShareRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareRepository shareRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public ResponseEntity SharePost(ShareRequestDto shareRequestDto) {

        User user = userRepository.findByMobileNumber(shareRequestDto.getUsername());
        if(user != null){
            if(Config.matches(shareRequestDto.getPassword(), user.getPassword())){
                Post post = postRepository.findById(shareRequestDto.getPostId());
                if(post != null){
                    if(post.getPostPrivacy() == PostPrivacy.PRIVATE){
                        return new ResponseEntity<>("Post is Private it can not be share", HttpStatus.NOT_FOUND);
                    }
                    shareRepository.save(
                            Share.builder()
                                    .share_On(shareRequestDto.getShare_on())
                                    .post(post)
                                    .user(user)
                                    .build()
                    );
                    return new ResponseEntity<>(post.getContain()+"\nPost Shared by "+user.getName()+" on \n"+shareRequestDto.getShare_on(), HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Post Not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);


    }
}
