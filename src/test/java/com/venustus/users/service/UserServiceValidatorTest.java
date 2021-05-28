package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.validator.ValidationManager;
import com.venustus.users.validator.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceValidatorTest {
    private final ValidationManager validationManager = mock(ValidationManager.class);
    private UserServiceValidator userServiceValidator;
    private UserDto userDto;
    private ValidationResult validationTrueResult;
    private ValidationResult validationFalseResult;

    @BeforeEach
    void setUp() {
        userServiceValidator = new UserServiceValidator(validationManager);
        userDto = new UserDto(1, "TestName", "TestLastName", "TestLogin", "test@email.pl");
        validationFalseResult = new ValidationResult(false, Collections.singletonList("Login contains word(s) which are not allowed in our service: kurde"));
        validationTrueResult = new ValidationResult(true, Collections.singletonList(""));
    }

    @Test
    void shouldThrowErrorValidatingUserDto() {
        //given
        when(validationManager.validate(userDto.getFirstName())).thenReturn(validationTrueResult);
        when(validationManager.validate(userDto.getLastName())).thenReturn(validationTrueResult);
        when(validationManager.validate(userDto.getLogin())).thenReturn(validationFalseResult);

        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> userServiceValidator.validateUserDto(userDto))
                .withMessage("[[Login contains word(s) which are not allowed in our service: kurde]]");
    }

    @Test
    void shouldNotThrowErrorValidatingUserDto() {
        //given
        when(validationManager.validate(userDto.getFirstName())).thenReturn(validationTrueResult);
        when(validationManager.validate(userDto.getLastName())).thenReturn(validationTrueResult);
        when(validationManager.validate(userDto.getLogin())).thenReturn(validationTrueResult);

        //when
        //then
        assertThatCode(() -> userServiceValidator.validateUserDto(userDto))
                .doesNotThrowAnyException();
    }
}