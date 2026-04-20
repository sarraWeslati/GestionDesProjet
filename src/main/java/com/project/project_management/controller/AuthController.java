package com.project.project_management.controller;

import com.project.project_management.model.User;
import com.project.project_management.repository.UserRepository;
import com.project.project_management.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository repo;
    private final JwtService jwtService;

    public AuthController(UserRepository repo, JwtService jwtService) {
        this.repo = repo;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User u = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!u.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return jwtService.generateToken(u.getUsername());
    }
}