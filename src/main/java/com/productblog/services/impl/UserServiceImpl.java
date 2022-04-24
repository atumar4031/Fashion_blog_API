package com.productblog.services.impl;

import com.productblog.dtos.UserDto;
import com.productblog.exception.UserAlreadyExist;
import com.productblog.exception.UserNotFound;
import com.productblog.models.User;
import com.productblog.repositories.UserRepository;
import com.productblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> selected = userRepository.findByEmail(userDto.getEmail());
               if (selected.isPresent())
                   throw new UserAlreadyExist("this email"+ userDto.getEmail()+" has been taken");

        User  user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        User createdUser = userRepository.save(user);

        return UserDto.builder()
                .firstName(createdUser.getFirstName())
                .lastName(createdUser.getLastName())
                .email(createdUser.getEmail())
                .role(createdUser.getRole())
                .build();
    }

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        User  user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFound("user not found"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setModify_at(
                LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return UserDto.builder()
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .build();

    }

    @Override
    public UserDto fetchUserById(long id) {
          User  user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFound("user not found"));

       return UserDto.builder()
               .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<UserDto> fetchUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users =  userRepository.findAll();
           for (User user: users)
               usersDto.add(new UserDto(user.getFirstName(),
                       user.getLastName(),user.getEmail(), user.getRole()));

       return usersDto;
    }
}
