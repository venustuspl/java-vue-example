package com.venustus.users.service;

import com.venustus.users.entity.Users;
import com.venustus.users.repository.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public List<Users> getUsersByAnyPartOfEmail(String email) {
        List<Users> usersList = usersRepository.findByAnyPartOfEmail(email);

        if (usersList.isEmpty()) {
            throw new IllegalArgumentException("This email address is never being used!");
        }

        return usersList;
    }

    public Users saveUser(Users users) {
        Optional<Users> user = usersRepository.findByEmail(users.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("This email address is already being used!");
        }
        return usersRepository.save(users);
    }

    public void deleteUsers(Users users) {
        usersRepository.delete(users);
    }
}
