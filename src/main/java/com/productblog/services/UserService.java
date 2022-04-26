package com.productblog.services;

import com.productblog.dtos.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> createUser(UserDto userDto);
    ResponseEntity<String> updateUser(UserDto userDto, long id);
    ResponseEntity<UserDto> fetchUserById(long id);
    ResponseEntity<UserDto> findUserByEmail(String email);
    ResponseEntity<List<UserDto>> fetchUsers();
}
