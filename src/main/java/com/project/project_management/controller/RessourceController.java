package com.project.project_management.controller;

import com.project.project_management.dto.RessourceDTO;
import com.project.project_management.service.RessourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/ressources")
@CrossOrigin("*")
public class RessourceController {

    private final RessourceService service;

    @Autowired
    public RessourceController(RessourceService service) {
        this.service = service;
    }

    @GetMapping
    public List<RessourceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RessourceDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RessourceDTO create(@Valid @RequestBody RessourceDTO ressource) {
        return service.save(ressource);
    }

    @PutMapping("/{id}")
    public RessourceDTO update(@PathVariable Long id, @Valid @RequestBody RessourceDTO ressource) {
        return service.update(id, ressource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
