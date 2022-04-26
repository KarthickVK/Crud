package com.tlc.crm.crud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public boolean isValid(final String name, final ConstraintValidatorContext constraintValidatorContext) {
        return (name.matches("[a-zA-Z\\s]*"));
    }
}
