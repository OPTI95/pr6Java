package com.example.Pr5.repositories;

import com.example.Pr5.model.Polzovatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

// UserRepository.java
public interface PolzovatelRepository extends JpaRepository<Polzovatel, Long> {
    @Query("SELECT u FROM Polzovatel u WHERE u.email = :email")
    Polzovatel findUserByEmail(@Param("email") String email);
}