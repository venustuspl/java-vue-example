package com.venustus.users.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationManager {
    private final List<UsersValidator> validators;

    public ValidationManager(List<UsersValidator> validators) {
        this.validators = validators;
    }

    public ValidationResult validate(String input) {
        List<String> errors = new ArrayList<>();

        validators.forEach(validator -> {
            errors.addAll(validator.validate(input));
        });

        return new ValidationResult(errors.isEmpty(), errors);
    }
}