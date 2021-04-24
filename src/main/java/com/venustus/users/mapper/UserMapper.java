package com.venustus.users.mapper;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public UserDto mapUserToUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    public User mapUserDtoToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
