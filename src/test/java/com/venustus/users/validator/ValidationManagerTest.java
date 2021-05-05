package com.venustus.users.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ValidationManagerTest {
    @Autowired
    List<UsersValidator> usersValidatorList;

    @Test
    void shouldReturnEmptyErrorList() {
        //given
        ValidationManager validationManager = new ValidationManager(usersValidatorList);

        //when
        ValidationResult validationResult = validationManager.validate("Text without forbidenn words");

        //then
        assertThat(validationResult.isValid()).isEqualTo(true);
    }
}