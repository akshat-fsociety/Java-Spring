package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.SubjectEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.ProfessorRepository;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.StudentRepository;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public SubjectService(SubjectRepository subjectRepository, ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    public SubjectEntity getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public SubjectEntity createNewSubject(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity);
    }

    public SubjectEntity assignProfessorToSubject(Long subjectId, Long professorId) {

        Optional<SubjectEntity> subjectEntity= subjectRepository.findById(subjectId);
        Optional<ProfessorEntity> professorEntity= professorRepository.findById(professorId);

        return subjectEntity.flatMap(subject ->
                professorEntity.map(professor -> {
                    subject.setProfessor(professor);
                    subjectRepository.save(subject);
                    return subject;
                })).orElse(null);
    }

    public SubjectEntity assignSubjectToStudent(Long subjectId, Long studentId) {
        Optional<SubjectEntity> subjectEntity= subjectRepository.findById(subjectId);
        Optional<StudentEntity> studentEntity= studentRepository.findById(studentId);

        return subjectEntity.flatMap(subject ->
                studentEntity.map(student -> {
                    subject.getSubjectTakenByStudent().add(student);
                    subjectRepository.save(subject);
                    return subject;
                })).orElse(null);
    }
}
