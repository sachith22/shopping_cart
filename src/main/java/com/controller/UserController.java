package com.controller;

import com.dto.ResponseDto;
import com.dto.user.SignInDto;
import com.dto.user.SignInResponseDto;
import com.entities.User;
import com.exceptions.CustomException;
import com.service.UserService;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * This is User Controller
 */
@RestController
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * User create method
     *
     * @param user
     * @return responseDto
     */
    @ApiOperation(value = "Add a User")
    @PostMapping("/add")
    public ResponseDto createUser(@RequestBody User user) throws CustomException {

        return userService.createUser(user);
    }

    /**
     * User Sign-in method
     *
     * @param signInDto
     * @return signInResponseDto with token
     * @throws CustomException
     */
    @ApiOperation(value = "User sign-in")
    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException {

        return userService.signIn(signInDto);
    }
}
