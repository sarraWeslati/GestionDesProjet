package com.project.project_management.service;

import com.project.project_management.dto.EmployeDTO;
import com.project.project_management.mapper.EmployeMapper;
import com.project.project_management.model.Employe;
import com.project.project_management.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    @Autowired
    public EmployeService(EmployeRepository employeRepository, EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }

    public List<EmployeDTO> getAll() {
        return employeRepository.findAll()
                .stream()
                .map(employeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeDTO getById(Long id) {
        return employeMapper.toDto(getEntityById(id));
    }

    public EmployeDTO save(EmployeDTO dto) {
        return employeMapper.toDto(employeRepository.save(employeMapper.toEntity(dto)));
    }

    public EmployeDTO update(Long id, EmployeDTO dto) {
        Employe employe = employeMapper.toEntity(dto);
        employe.setId(id);
        return employeMapper.toDto(employeRepository.save(employe));
    }

    public void delete(Long id) {
        employeRepository.deleteById(id);
    }

    public Employe getEntityById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe not found"));
    }
}
