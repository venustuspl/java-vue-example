package com.venustus.users.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidationManagerTest {
    @Autowired
    List<UsersValidator> usersValidatorList;

    @ParameterizedTest
    @ValueSource(strings = {"Hello, shit happens, chuck, kuku."})
    void shouldReturnEmptyErrorList(String input) {
        //given
        ValidationManager validationManager = new ValidationManager(usersValidatorList);

        //when
        ValidationResult validationResult = validationManager.validate(input);

        //then
        assertThat(validationResult.isValid()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, shit happens, chuk, kurde."})
    void shouldReturnNotEmptyErrorList(String input) {
        //given
        ValidationManager validationManager = new ValidationManager(usersValidatorList);

        //when
        ValidationResult validationResult = validationManager.validate(input);

        //then
        assertThat(validationResult.isValid()).isFalse();
    }
}