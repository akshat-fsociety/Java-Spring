package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
