package com.project.project_management.controller;

import com.project.project_management.dto.TacheDTO;
import com.project.project_management.service.TacheService;
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
@RequestMapping("/api/taches")
@CrossOrigin("*")
public class TacheController {

    private final TacheService tacheService;

    @Autowired
    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping
    public List<TacheDTO> getAll() {
        return tacheService.getAll();
    }

    @GetMapping("/{id}")
    public TacheDTO getById(@PathVariable Long id) {
        return tacheService.getById(id);
    }

    @PostMapping
    public TacheDTO create(@Valid @RequestBody TacheDTO tache) {
        return tacheService.save(tache);
    }

    @PutMapping("/{id}")
    public TacheDTO update(@PathVariable Long id, @Valid @RequestBody TacheDTO tache) {
        return tacheService.update(id, tache);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tacheService.delete(id);
    }
}
