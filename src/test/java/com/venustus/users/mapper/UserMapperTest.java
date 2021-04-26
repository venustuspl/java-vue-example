package com.venustus.users.mapper;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest {
    private static final String USER_NAME = "testUserName";
    private static final String USER_LAST_NAME = "testUserLastName";
    private static final String USER_LOGIN = "testUserLogin";
    private static final String USER_EMAIL = "test@user.email";
    private UserMapper userMapper;

    @BeforeEach
    void setup() {
        userMapper = new UserMapper();
    }

    @Test
    void shouldMapUserToUserDto() {
        //given
        User testUser = getUser();
        //when
        UserDto userDto = userMapper.mapUserToUserDto(testUser);
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(USER_NAME, userDto.getFirstName()),
                () -> Assertions.assertEquals(USER_LAST_NAME, userDto.getLastName()),
                () -> Assertions.assertEquals(USER_LOGIN, userDto.getLogin()),
                () -> Assertions.assertEquals(USER_EMAIL, userDto.getEmail()));
    }

    @Test
    void shouldMapUserDtoToUser() {
        //given
        UserDto testUser = getUserDto();
        //when
        User user = userMapper.mapUserDtoToUser(testUser);
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(USER_NAME, user.getFirstName()),
                () -> Assertions.assertEquals(USER_LAST_NAME, user.getLastName()),
                () -> Assertions.assertEquals(USER_LOGIN, user.getLogin()),
                () -> Assertions.assertEquals(USER_EMAIL, user.getEmail()));
    }

    private UserDto getUserDto() {
        return new UserDto(USER_NAME, USER_LAST_NAME, USER_LOGIN, USER_EMAIL);
    }

    private User getUser() {
        return new User(USER_NAME, USER_LAST_NAME, USER_LOGIN, USER_EMAIL);
    }
}