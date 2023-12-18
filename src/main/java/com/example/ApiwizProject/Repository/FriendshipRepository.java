package com.example.ApiwizProject.Repository;

import com.example.ApiwizProject.Model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    Friendship findById(int id);

}
