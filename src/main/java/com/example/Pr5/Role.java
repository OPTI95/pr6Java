package com.example.Pr5;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

// Role.java
public enum Role implements GrantedAuthority {
    USER, ADMIN, NEW_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }

}