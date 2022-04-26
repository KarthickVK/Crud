package com.tlc.crm.crud.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface Name {

    String message() default "i18n_validator_error_invalid_Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
