package com.example.ApiwizProject.Repository;


import com.example.ApiwizProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMobileNumber(long mobileNO);

}
