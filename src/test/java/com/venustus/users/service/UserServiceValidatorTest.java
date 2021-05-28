package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.validator.ValidationManager;
import com.venustus.users.validator.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceValidatorTest {
    private final ValidationManager validationManager = mock(ValidationManager.class);
    private UserServiceValidator userServiceValidator;
    private UserDto userDto;
    private ValidationResult validationNameResult;
    private ValidationResult validationLastNameResult;
    private ValidationResult validationLoginResult;

    @BeforeEach
    void setUp() {
        userServiceValidator = new UserServiceValidator(validationManager);
        userDto = new UserDto(1, "TestName", "TestLastName", "TestLogin", "test@email.pl");
        validationNameResult = new ValidationResult(false, Collections.singletonList("Login contains word(s) which are not allowed in our service: kurde" +
                ""));
        validationLastNameResult = new ValidationResult(true, Collections.singletonList(""));
        validationLoginResult = new ValidationResult(true, Collections.singletonList(""));
    }

    @Test
    void shoulThrowErrorValidatingUserDto() {
        //given
        when(validationManager.validate(userDto.getFirstName())).thenReturn(validationNameResult);
        when(validationManager.validate(userDto.getLastName())).thenReturn(validationLastNameResult);
        when(validationManager.validate(userDto.getLogin())).thenReturn(validationLoginResult);

        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> userServiceValidator.validateUserDto(userDto))
                .withMessage("[[Login contains word(s) which are not allowed in our service: kurde]]");
    }
}