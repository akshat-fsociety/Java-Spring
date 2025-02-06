package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.ProfessorRepository;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

    public ProfessorEntity assignStudentToProfessor(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return professorEntity.flatMap(professor ->
                studentEntity.map(student -> {
                    professor.getProfStudents().add(student);
                    professorRepository.save(professor);
                    return professor;
                })).orElse(null);
    }
}
