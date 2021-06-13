package com.service.impl;

import com.common.Constants;
import com.dto.ResponseDto;
import com.dto.user.SignInDto;
import com.dto.user.SignInResponseDto;
import com.entities.AuthenticationToken;
import com.entities.User;
import com.exceptions.AuthenticationFailException;
import com.exceptions.CustomException;
import com.repository.UserRepo;
import com.service.UserService;
import com.utils.Helper;
import com.utils.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.common.Constants.USER_CREATED;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private AuthenticationService authenticationService;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseDto createUser(User user) {

        if (Helper.notNull(userRepo.findByUsername(user.getUsername()))) {

            throw new CustomException("User already exists");
        }
        // encrypt the password
        String encryptedPassword = user.getPassword();
        try {
            encryptedPassword = hashPassword(user.getPassword());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.error("hashing password failed {}", e.getMessage());
        }

        User userData = new User(user.getFirstName(), user.getLastName(), user.getUsername(), encryptedPassword, user.getEmail());

        User createdUser;
        try {
            createdUser = userRepo.save(userData);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success
            return new ResponseDto(ResponseStatus.SUCCESS.toString(), USER_CREATED);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {

        User user = userRepo.findByUsername(signInDto.getUsername());
        if (!Helper.notNull(user)) {
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is correct
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                // password doesn't match
                throw new AuthenticationFailException(Constants.INCORRECT_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (!Helper.notNull(token)) {
            // token not present
            throw new CustomException("token not present");
        }

        return new SignInResponseDto("success", token.getToken());
    }

    /**
     * Generate hash password
     *
     * @param password
     * @return String hash password
     * @throws NoSuchAlgorithmException
     */
    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
