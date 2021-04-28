package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import com.venustus.users.mapper.UserMapper;
import com.venustus.users.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByAnyPartOfEmail(String email) {
        List<User> usersList = userRepository.findByAnyPartOfEmail(email);

        if (usersList.isEmpty()) {
            throw new IllegalArgumentException("This email address is never being used!");
        }

        return usersList;
    }

    public List<User> getUsersByAnyPartOfLogin(String login) {
        List<User> usersList = userRepository.findByAnyPartOfLogin(login);

        if (usersList.isEmpty()) {
            throw new IllegalArgumentException("This login is never being used!");
        }

        return usersList;
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        Optional<User> userWithExistsEmail = userRepository.findByEmail(userDto.getEmail());
        Optional<User> userWithExistsLogin = userRepository.findByLogin(userDto.getLogin());

        String exeptionMessage = "";

        if (userWithExistsEmail.isPresent()) {
            exeptionMessage = "This email address is already being used!";
        }
        if (userWithExistsLogin.isPresent()) {
            exeptionMessage = exeptionMessage + " This login is already being used!";
        }
        if (!exeptionMessage.isEmpty()) {
            throw new IllegalArgumentException(exeptionMessage);
        }
        return userRepository.save(userMapper.mapUserDtoToUser(userDto));
    }

    @Transactional
    public Optional<User> deleteUsers(Long id) {
        return userRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
