package com.productblog.services;

import com.productblog.dtos.UserDto;
import com.productblog.exception.UserAlreadyExist;
import com.productblog.exception.UserNotFound;
import com.productblog.models.User;
import com.productblog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    private UserDto userDto1;
    private UserDto userDto2;

    @BeforeEach
    void setUp() {
        List<User> userList = new ArrayList<>();
        String firstname = "Almustapha";
        String lastname = "Tukur";
        String email = "atumar4031@gmail.com";
        String role = "admin";

        userDto1 = UserDto.builder()
                .id(3L)
                .firstName("test first name")
                .lastName("test lastname")
                .email("test1@gmail.com")
                .role("admin")
                .build();

        userDto2 = UserDto.builder()
                .id(1L)
                .firstName("test first name")
                .lastName("test lastname")
                .email(email)
                .role("admin")
                .build();

        User newUser = User.builder()
                .id(3L)
                .firstName(firstname)
                .lastName(lastname)
                .email("test@gmail.com")
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .role(role)
                .build();

         User userById = User.builder()
                .id(1L)
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .role(role)
                .build();

        User userByEmail = User.builder()
                .id(2L)
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .role(role)
                .build();

        userList.add(userById);
        userList.add(userByEmail);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userById));
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(userByEmail));
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
    }

    @Test
    @DisplayName("TEST: create user")
    public void shouldReturnUserDto_afterCreatingNewUser(){
        ResponseEntity<String> responseEntity =  userService.createUser(userDto1);
        assertEquals("user created", Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    @DisplayName("TEST: Email already taken exception")
    public void shouldTest_whenEmailAlreadyExist(){
        assertThrows(UserAlreadyExist.class,
                () -> userService.createUser(userDto2), "Email has been taken");
    }

    @Test
    @DisplayName("TEST: updateUser")
    public void shouldReturnUserDto_afterUpdatingUser(){
        ResponseEntity<String> responseEntity =  userService.updateUser(userDto1, 1L);
        assertEquals("user updated", Objects.requireNonNull(responseEntity.getBody()));
    }

    @Test
    @DisplayName("TEST: find User by id")
    public void shouldReturnUserDtoWhen_validUserIdIsGiven(){
        long id = 1L;
        ResponseEntity<UserDto> responseEntity = userService.fetchUserById(id);
        assertEquals(id, Objects.requireNonNull(responseEntity.getBody()).getId());
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("TEST: User not found exception")
    public void shouldThrow_userNotFoundException(){

        assertThrows(
                UserNotFound.class,
                () -> userService.fetchUserById(3L),
                "user not found exception");
    }

    @Test
    @DisplayName("TEST: for find user by email")
    public void shouldReturnUserDtoWhen_validUserEmailIsGiven(){
        String email = "atumar4031@gmail.com";
        ResponseEntity<UserDto> responseEntity = userService.findUserByEmail(email);
        assertEquals(email, Objects.requireNonNull(responseEntity.getBody()).getEmail());
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("TEST: fetch all users")
    public void shouldReturnTheListOfAll_users(){
        ResponseEntity<List<UserDto>> responseEntity = userService.fetchUsers();
        assert(Objects.requireNonNull(responseEntity.getBody()).size() > 0);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }
}