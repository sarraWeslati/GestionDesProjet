package com.project.project_management.service;

import com.project.project_management.model.Employe;
import com.project.project_management.repository.EmployeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> getAll() {
        return employeRepository.findAll();
    }

    public Employe save(Employe e) {
        return employeRepository.save(e);
    }
}