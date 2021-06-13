package com.main.java;

import com.dto.ResponseDto;
import com.entities.User;
import com.service.UserService;
import com.utils.ResponseStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    void testAddNewUser() {
        boolean status;
        ResponseDto userDto1 = createUser("Joy","Root","joy123", "123456", "joy@aol.com");
        status = userDto1.getStatus().equalsIgnoreCase(ResponseStatus.success.toString());
        assertTrue(status);
        ResponseDto userDto2 =  createUser("John", "Kite", "kite123", "654321", "kite@aol.com");
        status = userDto2.getStatus().equalsIgnoreCase(ResponseStatus.success.toString());
        assertTrue(status);
    }

    public ResponseDto createUser(String firstName, String lastName, String username, String password, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return userService.createUser(user);
    }
}
