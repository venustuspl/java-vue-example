package com.venustus.users.validator;

import java.util.List;

public class ValidationResult {
    private final boolean isValid;
    private final List<String> error;

    public ValidationResult(boolean isValid, List<String> error) {
        this.isValid = isValid;
        this.error = error;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getError() {
        return error;
    }
}
