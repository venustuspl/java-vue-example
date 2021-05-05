package com.venustus.users.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationManager {
    private final List<UsersValidator> usersValidators;

    public ValidationManager(List<UsersValidator> usersValidators) {
        this.usersValidators = usersValidators;
    }

    public ValidationResult validate(String input) {
        List<String> errors = new ArrayList<>();

        usersValidators.forEach(validator -> errors.addAll(validator.validate(input)));

        return new ValidationResult(errors.isEmpty(), errors);
    }
}