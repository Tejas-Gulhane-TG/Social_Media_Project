package com.example.ApiwizProject.Model;


import com.example.ApiwizProject.Enum.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    NotificationType notificationType;

    @CreationTimestamp
    LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
