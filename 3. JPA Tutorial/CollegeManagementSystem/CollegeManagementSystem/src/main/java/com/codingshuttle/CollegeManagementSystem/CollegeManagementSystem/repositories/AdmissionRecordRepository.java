package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecordEntity, Long> {
}
