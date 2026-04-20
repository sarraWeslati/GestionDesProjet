package com.project.project_management.repository;

import com.project.project_management.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {
	
}
