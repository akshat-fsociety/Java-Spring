package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.DepartmentPrimeNumberValidation;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.DepartmentPasswordValidation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private long id;

    @NotNull
    @NotBlank(message = "Title cannot be blank!")
    @Size(max = 1234, message = "Title must not exceed 1234 characters!")
    private String title;

    @NotNull(message = "isActive cannot be null!")
    private boolean isActive;

    @PastOrPresent(message = "Date of creation should be greater then today's date or equal")
    private LocalDate createdAt;

    @Min(value = 1,message = "Number should be greater or equal to 1")
    @DepartmentPrimeNumberValidation
    private int primeNumber;

    @NotNull
    @NotBlank(message = "Password cannot be blank")
    @DepartmentPasswordValidation
    private String password;
}
