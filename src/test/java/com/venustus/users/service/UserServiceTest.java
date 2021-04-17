package com.venustus.users.service;

import com.venustus.users.entity.User;
import com.venustus.users.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = new User("test", "test", "test", "test@test.pl");
    }

    @Test
    void shouldThrowErrorSavingUserWithExistsId() {
        //given
        User testUser = new User("testUser", "testUser", "testLogin", "test@test.pl");
        when(userRepository.findByEmail("test@test.pl")).thenReturn(java.util.Optional.ofNullable(user));
        //when
        Throwable throwable = Assertions.catchThrowable(() -> userService.saveUser(testUser));
        //then
        assertThat(throwable).hasMessage("This email address is already being used!");
    }

    @Test
    void shouldReturnUserWhenSearchIsByAnyPartOfEmail() {
        //given
        User testUser = new User("testUser", "testUser", "testLogin", "test@test.pl");
        when(userRepository.findByAnyPartOfEmail("test")).thenReturn(Collections.singletonList(testUser));
        //when
        List<User> users = userService.getUsersByAnyPartOfEmail("test");
        //then
        assertThat(users).hasAtLeastOneElementOfType(User.class);
    }
}