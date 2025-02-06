package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.SubjectEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services.SubjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {

    private final SubjectService subjectService;


    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "/{subjectId}")
    public SubjectEntity getSubjectById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping
    public SubjectEntity createNewSubject(@RequestBody SubjectEntity subjectEntity){
        return subjectService.createNewSubject(subjectEntity);
    }

    @PutMapping(path = "/{subjectId}/professor/{professorId}")
    public SubjectEntity assignProfessorToSubject(@PathVariable Long subjectId,@PathVariable Long professorId){
        return subjectService.assignProfessorToSubject(subjectId,professorId);
    }

    @PutMapping(path = "/{subjectId}/student/{studentId}")
    public SubjectEntity assignSubjectToStudent(@PathVariable Long subjectId, @PathVariable Long studentId){
        return subjectService.assignSubjectToStudent(subjectId,studentId);
    }
}
