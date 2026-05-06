package com.project.project_management.controller;

import com.project.project_management.dto.AffectationDTO;
import com.project.project_management.service.AffectationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<AffectationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AffectationDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public AffectationDTO create(@Valid @RequestBody AffectationDTO affectation) {
        return service.save(affectation);
    }

    @PutMapping("/{id}")
    public AffectationDTO update(@PathVariable Long id, @Valid @RequestBody AffectationDTO affectation) {
        return service.update(id, affectation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
