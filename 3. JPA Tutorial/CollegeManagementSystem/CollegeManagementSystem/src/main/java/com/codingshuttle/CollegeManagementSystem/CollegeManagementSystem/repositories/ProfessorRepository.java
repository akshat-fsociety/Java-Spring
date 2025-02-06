package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
