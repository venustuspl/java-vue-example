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

    public Users saveUser(Users users) {
        Optional<Users> user = usersRepository.findByEmail(users.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("User exists");
        }
        return usersRepository.save(users);
    }
}
