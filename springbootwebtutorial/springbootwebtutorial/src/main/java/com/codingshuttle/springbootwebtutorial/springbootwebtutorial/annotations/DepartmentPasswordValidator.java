package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentPasswordValidator implements ConstraintValidator<DepartmentPasswordValidation, String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\W).{10,}$"; // Regex for the criteria

    @Override
    public boolean isValid(String inputPassword, ConstraintValidatorContext context) {
        // Null or empty passwords are considered invalid
        if (inputPassword == null || inputPassword.isEmpty()) {
            return false;
        }

        // Match the password against the pattern
        return inputPassword.matches(PASSWORD_PATTERN);
    }

}
