package com.example.ApiwizProject.Model;


import com.example.ApiwizProject.Enum.PostPrivacy;
import com.example.ApiwizProject.Enum.PostType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    PostType postType;

    String contain;

    @Enumerated(EnumType.STRING)
    PostPrivacy postPrivacy;

    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comments> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Share> shares = new ArrayList<>();

    @OneToMany(mappedBy = "repostedPost", cascade = CascadeType.ALL)
    List<Repost> reposts = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Likes> likes = new ArrayList<>();


}
