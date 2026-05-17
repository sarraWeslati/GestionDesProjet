package com.project.project_management.controller;

import com.project.project_management.model.User;
import com.project.project_management.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return repo.save(user);

    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User u = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!u.getPassword().equals(user.getPassword())) {

            throw new RuntimeException("Wrong password");

        }

        return u;
    }
}