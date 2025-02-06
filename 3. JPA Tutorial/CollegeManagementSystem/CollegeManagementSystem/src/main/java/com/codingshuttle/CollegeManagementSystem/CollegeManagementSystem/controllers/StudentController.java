package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.controllers;

import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public StudentEntity createNewStudent(@RequestBody StudentEntity studentEntity){
        return studentService.createNewStudent(studentEntity);
    }
}
