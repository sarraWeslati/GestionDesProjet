package com.project.project_management.controller;

import com.project.project_management.model.Employe;
import com.project.project_management.service.EmployeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin("*")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> getAll() {
        return employeService.getAll();
    }

    @PostMapping
    public Employe create(@RequestBody Employe e) {
        return employeService.save(e);
    }
}