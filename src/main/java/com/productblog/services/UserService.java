package com.productblog.services;

import com.productblog.dtos.UserDto;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);
    void updateUser(UserDto userDto, long id);
    UserDto fetchUserById(long id);
    List<UserDto> fetchUsers();
}
