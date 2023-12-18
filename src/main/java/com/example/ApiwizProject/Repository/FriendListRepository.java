package com.example.ApiwizProject.Repository;

import com.example.ApiwizProject.Model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Integer> {
}
