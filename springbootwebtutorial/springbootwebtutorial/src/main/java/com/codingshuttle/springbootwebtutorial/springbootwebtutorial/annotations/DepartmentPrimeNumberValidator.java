package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentPrimeNumberValidator implements ConstraintValidator<DepartmentPrimeNumberValidation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;
        return checkPrimeNumber(value);
    }
    private boolean checkPrimeNumber(int value){
        int count = 0;
        for(int i = 2;i*i<=value;i++){
            if(value % i == 0){
                count += 1;
                break;
            }
        }
        if(count == 0) return true;
        else return false;
    }
}
