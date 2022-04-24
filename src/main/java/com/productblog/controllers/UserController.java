package com.productblog.controllers;


import com.productblog.dtos.UserDto;
import com.productblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto){
        return  userService.createUser(userDto);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto){
        return userService.updateUser(userDto, id);
    }

    @GetMapping("/{uderId}")
    public UserDto findUserById(@PathVariable("userId") long id){
        return userService.fetchUserById(id);
    }

    @GetMapping("/all")
    public List<UserDto> findUsers(){
        return userService.fetchUsers();
    }


}
