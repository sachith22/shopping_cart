package com.service;

import com.dto.ResponseDto;
import com.dto.user.SignInDto;
import com.dto.user.SignInResponseDto;
import com.entities.User;

public interface UserService {
    ResponseDto createUser(User user);

    SignInResponseDto signIn(SignInDto signInDto);
}
