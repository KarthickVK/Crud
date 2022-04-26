package com.tlc.crm.crud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, String> {
    @Override
    public boolean isValid(String age, ConstraintValidatorContext constraintValidatorContext) {
        return (age.matches("^(?:1[8-9]|[2-5][0-9])$"));
    }
}