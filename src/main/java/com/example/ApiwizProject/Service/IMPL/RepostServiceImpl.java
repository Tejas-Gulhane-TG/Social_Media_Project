package com.example.ApiwizProject.Service.IMPL;


import com.example.ApiwizProject.DTO.RequestDto.RepostRequestDto;
import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Model.Post;
import com.example.ApiwizProject.Model.Repost;
import com.example.ApiwizProject.Model.User;
import com.example.ApiwizProject.Repository.PostRepository;
import com.example.ApiwizProject.Repository.RepostRepository;
import com.example.ApiwizProject.Repository.UserRepository;
import com.example.ApiwizProject.Security.Config;
import com.example.ApiwizProject.Service.RepostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RepostServiceImpl implements RepostService {

    @Autowired
    RepostRepository repostRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public ResponseEntity RepostCreate(RepostRequestDto repostRequestDto) {
        User user = userRepository.findByMobileNumber(repostRequestDto.getUsername());
        if(user != null){
            if(Config.matches(repostRequestDto.getPassword(), user.getPassword())){
                Post post = postRepository.findById(repostRequestDto.getPostId());
                if(post != null){
                    if(post.getPostPrivacy() == PostPrivacy.PRIVATE){
                        return new ResponseEntity<>("Post is Private", HttpStatus.NOT_FOUND);
                    }
                    repostRepository.save(
                            Repost.builder()
                                    .repostedPost(post)
                                    .contain(repostRequestDto.getContain())
                                    .postPrivacy(repostRequestDto.getPostPrivacy())
                                    .user(user)
                                    .build()
                    );
                    return new ResponseEntity<>(post.getContain()+"\nPost reposted by \n"+user.getName(), HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Post Not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Wrong password", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
