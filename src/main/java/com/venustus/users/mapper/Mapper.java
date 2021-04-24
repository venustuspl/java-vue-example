package com.venustus.users.mapper;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User mapUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
