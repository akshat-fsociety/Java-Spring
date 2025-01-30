package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(
        validatedBy = {DepartmentPrimeNumberValidator.class}
)
public @interface DepartmentPrimeNumberValidation {
    String message() default "NOT A PRIME NUMBER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
