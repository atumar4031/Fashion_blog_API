package com.productblog.controllers;


import com.productblog.dtos.UserDto;
import com.productblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blog/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createuser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>(
                "User created",
                HttpStatus.OK
        );
    }

}
