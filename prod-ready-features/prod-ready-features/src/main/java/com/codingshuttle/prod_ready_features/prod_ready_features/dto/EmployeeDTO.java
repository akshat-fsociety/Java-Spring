package com.codingshuttle.prod_ready_features.prod_ready_features.dto;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private int age;

    private String role;

    private Double salary;

    LocalDate dateOfJoining;

    boolean isActive;
}
