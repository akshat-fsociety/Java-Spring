package com.codingshuttle.CollegeManagementSystem.CollegeManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admission_records")
public class AdmissionRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int fees;

    @OneToOne
    @JoinColumn(name = "student_admission_record")
    private StudentEntity studentRecord;
}
