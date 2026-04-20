package com.project.project_management.controller;

import com.project.project_management.model.Tache;
import com.project.project_management.service.TacheService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/taches")
@CrossOrigin("*")
public class TacheController {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping
    public List<Tache> getAll() {
        return tacheService.getAll();
    }

    @PostMapping
    public Tache create(@RequestBody Tache tache) {
        return tacheService.save(tache);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tacheService.delete(id);
    }
}