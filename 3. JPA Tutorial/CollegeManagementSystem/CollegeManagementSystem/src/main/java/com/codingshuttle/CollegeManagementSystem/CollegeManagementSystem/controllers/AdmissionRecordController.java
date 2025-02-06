package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.AdmissionRecordEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admissions")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping(path = "/{admissionRecordId}")
    public AdmissionRecordEntity getAdmissionRecordById(@PathVariable Long admissionRecordId){
        return admissionRecordService.getAdmissionRecordById(admissionRecordId);
    }

    @PostMapping
    public AdmissionRecordEntity createNewAdmissionRecord(@RequestBody AdmissionRecordEntity admissionRecordEntity){
        return admissionRecordService.createNewAdmissionRecord(admissionRecordEntity);
    }

    @PutMapping(path = "/{admissionRecordId}/student/{studentId}")
    public AdmissionRecordEntity assignStudentToAdmissionRecord(@PathVariable Long admissionRecordId,
                                                                @PathVariable Long studentId){
        return admissionRecordService.assignStudentToAdmissionRecord(admissionRecordId, studentId);
    }
}
