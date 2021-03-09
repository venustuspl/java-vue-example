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
        try {
            log.info("UsersController:  save users");
            Users resource = usersService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resource);
        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//            HttpHeaders header = new HttpHeaders();
//            header.add("exception",exception.getMessage().toString());
//            return ResponseEntity
//                    .status(HttpStatus.FORBIDDEN)
//                    .headers(header)
//                    .body(exception.getMessage());
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
