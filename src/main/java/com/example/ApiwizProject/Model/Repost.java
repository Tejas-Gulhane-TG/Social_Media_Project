package com.example.ApiwizProject.Model;


import com.example.ApiwizProject.Enum.PostPrivacy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Repost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String contain;

    @Enumerated(EnumType.STRING)
    PostPrivacy postPrivacy;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post repostedPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
