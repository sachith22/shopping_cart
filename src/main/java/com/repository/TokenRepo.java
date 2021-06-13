package com.repository;

import com.entities.AuthenticationToken;
import com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findTokenByUser(User user);

    AuthenticationToken findTokenByToken(String token);
}
