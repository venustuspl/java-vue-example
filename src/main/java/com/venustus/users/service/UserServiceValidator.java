package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.validator.ValidationManager;
import com.venustus.users.validator.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceValidator {
    private final ValidationManager validationManager;

    public UserServiceValidator(ValidationManager validationManager) {
        this.validationManager = validationManager;
    }

    public void validateUserDto(UserDto userDto) {
        List<ValidationResult> results = new ArrayList<>();

        results.add(validationManager.validate(userDto.getFirstName()));
        results.add(validationManager.validate(userDto.getLastName()));
        results.add(validationManager.validate(userDto.getLogin()));

        List<String> mainResult = results.stream()
                .filter(r -> !r.isValid())
                .map(r -> r.getError().toString())
                .collect(Collectors.toList());

        if (!mainResult.isEmpty()) {
            throw new IllegalArgumentException(mainResult.toString());
        }
    }
}
