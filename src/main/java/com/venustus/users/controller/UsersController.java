package com.venustus.users.controller;

import com.venustus.users.entity.Users;
import com.venustus.users.links.UserLinks;
import com.venustus.users.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = UserLinks.LIST_USERS_BY_EMAIL)
    public ResponseEntity<?> listUsersByEmail(@RequestParam String email) {
        try {
            log.info("UsersController:  list users by email");
            List<Users> resource = usersService.getUsersByAnyPartOfEmail(email);
            return ResponseEntity.ok(resource);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = UserLinks.ADD_USER)
    public ResponseEntity<?> saveUser(@RequestBody Users user) {
        try {
            log.info("UsersController:  save users");
            Users resource = usersService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resource);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = UserLinks.DELETE_USER)
    public ResponseEntity<?> deleteUser(@RequestBody Long id) {
        try {
            log.info("UsersController:  delete users");
            Optional<Users> resource = usersService.deleteUsers(id);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resource);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
