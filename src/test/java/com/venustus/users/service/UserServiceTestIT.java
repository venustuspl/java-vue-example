package com.venustus.users.service;

import com.venustus.users.entity.User;
import com.venustus.users.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        User user = getUserToSave();
        //when
        User savedUser = userService.saveUser(user);
        //then
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    private User getUserToSave() {
        return new User("testUser", "testUser", "test@test.pl");
    }
}