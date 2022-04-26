package com.tlc.crm.crud.validation;

import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.code.ErrorCodes;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.status.EmployeeErrorCodes;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * HibernateValidation to the inputs.
 *
 * @author KarthickV
 */
public class HibernateValidation {

    private static final ModelValidator MODEL_VALIDATOR = ValidatorAccess.get();

    /**
     * Validate the employee object
     *
     * @param employee
     * @param clazz
     */
    public static void validate(final Employee employee, final Class clazz) {

            if (!MODEL_VALIDATOR.isValid(employee, clazz)) {
                throw ErrorCode.get(EmployeeErrorCodes.VALIDATE_FAILED);
            }
    }
}
