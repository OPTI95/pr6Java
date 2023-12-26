package com.example.Pr5;

import com.example.Pr5.model.Polzovatel;
import com.example.Pr5.repositories.PolzovatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// UserService.java
@Service
public class UserService implements UserDetailsService {

    private final PolzovatelRepository userRepository;

    @Autowired
    public UserService(PolzovatelRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Polzovatel getUserByEmail(String email){
        Polzovatel user = userRepository.findUserByEmail(email);
        return user;
    }
    public Polzovatel createUser(Polzovatel user){
        Polzovatel newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Polzovatel user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("No user found with email");
        }
        List<String> roles = Arrays.asList(user.getRole());
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();

        return userDetails;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
