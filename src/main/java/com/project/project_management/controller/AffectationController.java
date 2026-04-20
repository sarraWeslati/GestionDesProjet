package com.project.project_management.controller;

import com.project.project_management.model.Affectation;
import com.project.project_management.service.AffectationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/affectations")
@CrossOrigin("*")
public class AffectationController {

    private final AffectationService service;

    public AffectationController(AffectationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Affectation> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Affectation create(@RequestBody Affectation a) {
        return service.save(a);
    }
}