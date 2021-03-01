package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private User createUser;

    @Mock
    private List<User> userList;


    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();

        user = new User();
        user.setId(1);
        user.setUserName("Henk");

        User user2 = new User();
        user2.setId(2);

        createUser = new User();
        createUser.setUserName("Paul");
        createUser.setId(3);
        createUser.setPassword("123");

        userList.add(user);
    }

    @Test
    void getUserByIdTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        Optional<User> u = userService.getUserById(1);

        assertEquals(u, java.util.Optional.of(user));
    }

    @Test
    void getUserByNameTest() {
        Mockito.when(userRepository.findByUserName("Henk")).thenReturn(java.util.Optional.of(user));
        Optional<User> u = userService.getUserByName("Henk");

        assertEquals(u, java.util.Optional.of(user));

    }

    @Test
    void getAllUsersTest() {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Collection<User> list = userService.getAllUsers();

        assertEquals(list, userList);
    }

//    @Test
//    void createUserTest() {
//        Mockito.when(userRepository.findByUserName("Paul")).thenReturn(Optional.empty());
//        long l = userService.createUser(this.createUser);
//        Optional<User> u = userService.getUserById(l);
//
//        assertEquals(u.get().getUserName(), "Paul");
//
//    }
}
