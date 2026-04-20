package com.project.project_management.controller;

import com.project.project_management.model.Projet;
import com.project.project_management.service.ProjetService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin("*")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public List<Projet> getAll() {
        return projetService.getAll();
    }

    @PostMapping
    public Projet create(@RequestBody Projet projet) {
        return projetService.save(projet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projetService.delete(id);
    }
    
    @GetMapping("/{id}/cout")
    public double getCout(@PathVariable Long id) {
        return projetService.calculCoutProjet(id);
    }
    
    @GetMapping("/{id}/budget-status")
    public String checkBudget(@PathVariable Long id) {

        Projet p = projetService.getById(id); // ou repository
        double cout = projetService.calculCoutProjet(id);

        if (cout > p.getBudget()) {
            return "⚠️ Budget dépassé";
        } else {
            return "✅ Budget OK";
        }
    }
}