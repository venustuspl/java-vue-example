package com.venustus.users.service;

import com.venustus.users.entity.User;
import com.venustus.users.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Transactional
    public User saveUser(User user) {
        Optional<User> userWithExistsEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> userWithExistsLogin = userRepository.findByLogin(user.getLogin());

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
        return userRepository.save(user);
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
