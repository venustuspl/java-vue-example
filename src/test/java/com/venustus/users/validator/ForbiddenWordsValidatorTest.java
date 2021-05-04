package com.venustus.users.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ForbiddenWordsValidatorTest {
    private ForbiddenWordsValidator forbiddenWordsValidator;

    @BeforeEach
    public void init() {
        forbiddenWordsValidator = new ForbiddenWordsValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, shit happens, chuk, kurde."})
    void shouldContainForbiddenWord(String input) {
        // given
        // when
        List<String> list = forbiddenWordsValidator.validate(input);

        // then
        assertThat(list.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, shit happens, chuck, kuku."})
    void shouldNotContainForbiddenWord(String input) {
        // given
        // when
        List<String> list = forbiddenWordsValidator.validate(input);

        // then
        assertThat(list.size()).isEqualTo(0);
    }
}