package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.AdmissionRecordEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.AdmissionRecordRepository;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionRecordId) {
        return admissionRecordRepository.findById(admissionRecordId).orElse(null);
    }


    public AdmissionRecordEntity createNewAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity assignStudentToAdmissionRecord(Long admissionRecordId, Long studentId) {
        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        return admissionRecordEntity.flatMap(admissionRecord ->
                studentEntity.map(student ->{
                    admissionRecord.setStudentRecord(student);
                    return admissionRecordRepository.save(admissionRecord);
                })).orElse(null);
    }
}
