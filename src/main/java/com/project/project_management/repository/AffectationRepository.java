package com.project.project_management.repository;

import com.project.project_management.model.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {
	
}