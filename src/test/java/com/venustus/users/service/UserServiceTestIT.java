package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import com.venustus.users.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTestIT {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    @AfterEach
    void clear() {
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveUserInDb() {
        //given
        UserDto userDto = getUserDtoToSave();
        //when
        User savedUser = userService.saveUser(userDto);
        //then
        assertEquals(userDto.getEmail(), savedUser.getEmail());
    }

    @Test
    void shouldDeleteUserFromDB() {
        //given
        UserDto userDto = getUserDtoToSave();
        userService.saveUser(userDto);
        //when
        userRepository.deleteAll();
        //then
        assertThat(userService.getUsers()).isEmpty();
    }

    private UserDto getUserDtoToSave() {
        return new UserDto("testUser", "testUser", "testLogin", "test@test.pl");
    }
}