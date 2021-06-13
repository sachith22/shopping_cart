package com.main.java.controller;

import com.entities.User;
import com.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("Sachith");
        user.setUsername("sachith123");

        userService.createUser(user);
        assertTrue(user.getId() > 0);
    }
}
