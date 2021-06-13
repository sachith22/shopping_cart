package com.service.impl;

import com.common.Constants;
import com.entities.AuthenticationToken;
import com.entities.User;
import com.exceptions.AuthenticationFailException;
import com.repository.TokenRepo;
import com.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    TokenRepo repository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        LOGGER.info("get user token");
        return repository.findTokenByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        LOGGER.info("authenticating..");
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(Constants.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(Constants.AUTH_TOKEN_INVALID);
        }
    }
}
