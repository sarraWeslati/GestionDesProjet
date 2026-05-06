package com.project.project_management.controller;

import com.project.project_management.dto.EmployeDTO;
import com.project.project_management.service.EmployeService;
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
@RequestMapping("/api/employes")
@CrossOrigin("*")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<EmployeDTO> getAll() {
        return employeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeDTO getById(@PathVariable Long id) {
        return employeService.getById(id);
    }

    @PostMapping
    public EmployeDTO create(@Valid @RequestBody EmployeDTO employe) {
        return employeService.save(employe);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @Valid @RequestBody EmployeDTO employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeService.delete(id);
    }
}
