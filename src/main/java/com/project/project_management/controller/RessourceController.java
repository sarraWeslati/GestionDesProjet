package com.project.project_management.controller;

import com.project.project_management.model.Ressource;
import com.project.project_management.service.RessourceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ressources")
@CrossOrigin("*")
public class RessourceController {

    private final RessourceService service;

    public RessourceController(RessourceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ressource> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Ressource create(@RequestBody Ressource r) {
        return service.save(r);
    }
}