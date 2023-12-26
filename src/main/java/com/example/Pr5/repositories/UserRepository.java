package com.example.Pr5.repositories;

import com.example.Pr5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
