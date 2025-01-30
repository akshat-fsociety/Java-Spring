package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext constraintValidatorContext) {
        if(inputValue==null)return false;
        List<String> roles = List.of("USER","ADMIN","HR");
        return roles.contains(inputValue);
    }
}
