package com.example.ApiwizProject.Repository;

import com.example.ApiwizProject.Model.Massage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MassageRepository extends JpaRepository<Massage, Integer> {

}
