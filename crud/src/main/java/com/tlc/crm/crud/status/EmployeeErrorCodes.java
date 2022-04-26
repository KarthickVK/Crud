package com.tlc.crm.crud.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * EmployeeErrorCodes
 *
 * @author KarthickV
 */
public enum EmployeeErrorCodes implements ErrorCodeProvider {

    INVALID_ID(0x01),
    VALIDATE_FAILED(0x02);

    private final int code;

    EmployeeErrorCodes(final int code) {
        this.code = EmployeeErrorCodeGroup.ERROR_CODE_GROUP.getConvertedCode(code);
    }

    @Override
    public int getCode() {
        return code;
    }

    private static class EmployeeErrorCodeGroup implements ErrorCodeGroup {
        private static final ErrorCodeGroup ERROR_CODE_GROUP = new EmployeeErrorCodeGroup();

        @Override
        public int getPrefix() {
            return 0x10_0_0000;
        }
    }
}
