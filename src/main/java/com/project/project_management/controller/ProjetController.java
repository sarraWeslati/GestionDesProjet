package com.project.project_management.controller;

import com.project.project_management.dto.ProjetDTO;
import com.project.project_management.dto.RapportFinancierDTO;
import com.project.project_management.service.ProjetService;
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
@RequestMapping("/api/projets")
@CrossOrigin("*")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public List<ProjetDTO> getAll() {
        return projetService.getAll();
    }

    @GetMapping("/{id}")
    public ProjetDTO getById(@PathVariable Long id) {
        return projetService.getById(id);
    }

    @PostMapping
    public ProjetDTO create(@Valid @RequestBody ProjetDTO projet) {
        return projetService.save(projet);
    }

    @PutMapping("/{id}")
    public ProjetDTO update(@PathVariable Long id, @Valid @RequestBody ProjetDTO projet) {
        return projetService.update(id, projet);
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
        RapportFinancierDTO rapport = projetService.getRapportFinancier(id);
        return rapport.getBudgetDepasse() ? "Budget depasse" : "Budget OK";
    }

    @GetMapping("/{id}/rapport-financier")
    public RapportFinancierDTO getRapportFinancier(@PathVariable Long id) {
        return projetService.getRapportFinancier(id);
    }
}
