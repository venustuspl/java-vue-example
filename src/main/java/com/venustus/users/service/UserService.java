package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import com.venustus.users.mapper.UserMapper;
import com.venustus.users.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserServiceValidator userServiceValidator;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       UserServiceValidator userServiceValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userServiceValidator = userServiceValidator;
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
        String exeptionMessage = "";
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            exeptionMessage = "This email address is already being used!";
        }
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            exeptionMessage = exeptionMessage + " This login is already being used!";
        }
        if (!exeptionMessage.isEmpty()) {
            throw new IllegalArgumentException(exeptionMessage);
        }

        userServiceValidator.validateUserDto(userDto);

        return userRepository.save(userMapper.mapUserDtoToUser(userDto));
    }

    public static void setEmptyFieldOfUserForUpdate(User userForUpdate, UserDto userDto) {
        if (!userDto.getFirstName().isEmpty()) {
            userForUpdate.setFirstName(userDto.getFirstName());
        }
        if (!userDto.getLastName().isEmpty()) {
            userForUpdate.setLastName(userDto.getLastName());
        }
        if (!userDto.getLogin().isEmpty()) {
            userForUpdate.setLogin(userDto.getLogin());
        }
        if (!userDto.getEmail().isEmpty()) {
            userForUpdate.setEmail(userDto.getEmail());
        }
    }

    @Transactional
    public Optional<User> deleteUsers(Long id) {
        return userRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public User updateUser(UserDto userDto) {
        String exeptionMessage = "";
        Long userForUpdateId = userDto.getId();

        User userForUpdate = userRepository.findById(userDto.getId())
                .orElseThrow(() ->
                        new NoSuchElementException("The user with id " + userForUpdateId + " does not exist in DB")
                );

        userServiceValidator.validateUserDto(userDto);

        if (userRepository.findByEmail(userDto.getEmail()).isPresent() && !userForUpdate.getEmail().equals(userDto.getEmail())) {
            exeptionMessage = "This email address is already being used!";
        }
        if (userRepository.findByLogin(userDto.getLogin()).isPresent() && !userForUpdate.getLogin().equals(userDto.getLogin())) {
            exeptionMessage = exeptionMessage + " This login is already being used!";
        }
        if (!exeptionMessage.isEmpty()) {
            throw new IllegalArgumentException(exeptionMessage);
        }

        setEmptyFieldOfUserForUpdate(userForUpdate, userDto);

        return userForUpdate;
    }
}
