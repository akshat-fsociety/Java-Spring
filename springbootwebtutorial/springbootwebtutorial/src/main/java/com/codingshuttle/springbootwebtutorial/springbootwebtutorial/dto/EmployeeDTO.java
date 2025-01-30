package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private long id;

    @NotBlank(message = "Name cannot be Blank")
    @Size(min=3, max=10, message = "Name must be in range [3,10]")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid email")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value=18, message = "Age > 18")
    @Max(value=60, message = "Age < 60")
    private int age;

    @NotBlank(message = "role cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$")
//    Now we will create the custom annotations check annotations package
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary must be DDDDDD.DD")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "Joining should be either past or present")
    LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    boolean isActive;
}
