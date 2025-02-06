package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
