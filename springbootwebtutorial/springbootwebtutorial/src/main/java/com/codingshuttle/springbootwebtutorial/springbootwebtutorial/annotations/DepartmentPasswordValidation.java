package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(
        validatedBy = {DepartmentPasswordValidator.class}
)
public @interface DepartmentPasswordValidation {
    String message() default "Password Does not match the required validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
