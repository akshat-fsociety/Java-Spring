package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public StudentEntity createNewStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }
}
