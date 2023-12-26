package com.example.Pr5.controllers;

import com.example.Pr5.model.User;
import com.example.Pr5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/{id}")
    public String showUserDetails(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/userDetails";
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/userForm";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/editUserForm";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
