package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "studentRecord")
    @JsonIgnore
    private AdmissionRecordEntity recordedStudent;

    @ManyToMany(mappedBy = "profStudents")
    private Set<ProfessorEntity> studentProfessors;

    @ManyToMany(mappedBy = "subjectTakenByStudent")
    private Set<SubjectEntity> studentEnrolledInSubject;

}
