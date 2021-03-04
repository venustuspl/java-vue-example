package com.venustus.users.controller;

import com.venustus.users.entity.Users;
import com.venustus.users.links.UserLinks;
import com.venustus.users.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() {
        log.info("UsersController:  list users");
        List<Users> resource = usersService.getUsers();
        return ResponseEntity.ok(resource);
    }

    @PostMapping(path = UserLinks.ADD_USER)
    public ResponseEntity<?> saveUser(@RequestBody Users user) {
        log.info("UsersController:  list users");
        try {
            Users resource = usersService.saveUser(user);
            return ResponseEntity.ok(resource);
        } catch (Exception exception) {
            return ResponseEntity.ok(user);
        }
    }
}
