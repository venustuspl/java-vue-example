package com.venustus.users.controller;

import com.venustus.users.entity.User;
import com.venustus.users.links.UserLinks;
import com.venustus.users.service.UserService;
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
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() {
        log.info("UsersController:  list users");
        List<User> resource = userService.getUsers();
        return ResponseEntity.ok(resource);
    }

    @GetMapping(path = UserLinks.LIST_USERS_BY_EMAIL)
    public ResponseEntity<?> listUsersByEmail(@RequestParam String email) {
        try {
            log.info("UsersController:  list users by email");
            List<User> resource = userService.getUsersByAnyPartOfEmail(email);
            return ResponseEntity.ok(resource);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = UserLinks.ADD_USER)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            log.info("UsersController:  save users");
            System.out.println(user.toString());
            User resource = userService.saveUser(new User(user.getFirstName(), user.getLastName(), user.getEmail()));
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
            Optional<User> resource = userService.deleteUsers(id);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resource);
        } catch (Exception exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
