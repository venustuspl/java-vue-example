package com.venustus.users.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class ForbiddenWordsValidator implements UsersValidator {

    List<String> forbiddenWordService = new ArrayList<>(Arrays.asList("kurde", "chuk"));

    @Override
    public List<String> validate(String input) {
        List<String> errors = forbiddenWordService.stream()
                .filter(forbiddenWord -> input.toLowerCase().contains(forbiddenWord))
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            return Collections.singletonList("Login contains word(s) which are not allowed in our service: "
                    + errors);
        }
        return errors;
    }
}