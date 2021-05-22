package com.venustus.users.service;

import com.venustus.users.dto.UserDto;
import com.venustus.users.entity.User;
import com.venustus.users.mapper.UserMapper;
import com.venustus.users.repository.UserRepository;
import com.venustus.users.validator.ValidationManager;
import com.venustus.users.validator.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ValidationManager validationManager;

    public UserService(UserRepository userRepository, UserMapper userMapper, ValidationManager validationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validationManager = validationManager;
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

        List<ValidationResult> results = new ArrayList<>();

        ValidationResult validationFirstNameResult = validationManager.validate(userDto.getFirstName());
        results.add(validationFirstNameResult);
        ValidationResult validationLastNameinResult = validationManager.validate(userDto.getLastName());
        results.add(validationLastNameinResult);
        ValidationResult validationLoginResult = validationManager.validate(userDto.getLogin());
        results.add(validationLoginResult);


        List<String> mainResult = results.stream()
                .filter(r -> !r.isValid())
                .map(r -> r.getError().toString())
                .collect(Collectors.toList());

        if (!mainResult.isEmpty()) {
            throw new IllegalArgumentException(mainResult.toString());
        }


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
    public User updateUser(UserDto userDto) {
        Long userForUpdateId = userDto.getId();

        User userForUpdate = (User) userRepository.findById(userDto.getId())
                .orElseThrow(() ->
                        new NoSuchElementException("The user with id " + userForUpdateId + " does not exist in DB")
                );

        Optional<User> userWithExistsEmail = userRepository.findByEmail(userDto.getEmail());
        Optional<User> userWithExistsLogin = userRepository.findByLogin(userDto.getLogin());

        List<ValidationResult> results = new ArrayList<>();

        ValidationResult validationFirstNameResult = validationManager.validate(userDto.getFirstName());
        results.add(validationFirstNameResult);
        ValidationResult validationLastNameinResult = validationManager.validate(userDto.getLastName());
        results.add(validationLastNameinResult);
        ValidationResult validationLoginResult = validationManager.validate(userDto.getLogin());
        results.add(validationLoginResult);

        List<String> mainResult = results.stream()
                .filter(r -> !r.isValid())
                .map(r -> r.getError().toString())
                .collect(Collectors.toList());

        if (!mainResult.isEmpty()) {
            throw new IllegalArgumentException(mainResult.toString());
        }


        String exeptionMessage = "";

        if (userWithExistsEmail.isPresent() && !userForUpdate.getEmail().equals(userDto.getEmail())) {
            exeptionMessage = "This email address is already being used!";
        }
        if (userWithExistsLogin.isPresent() && !userForUpdate.getLogin().equals(userDto.getLogin())) {
            exeptionMessage = exeptionMessage + " This login is already being used!";
        }
        if (!exeptionMessage.isEmpty()) {
            throw new IllegalArgumentException(exeptionMessage);
        }

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

        return userForUpdate;
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
