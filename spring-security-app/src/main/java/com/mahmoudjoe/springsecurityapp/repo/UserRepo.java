package com.mahmoudjoe.springsecurityapp.repo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepo {
    private final List<UserDetails> APP_USERS = Arrays.asList(
            new User(
                    "mahmoudjoe"
                    ,"pass"
                    , Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "mahmoud3"
                    ,"pass3"
                    , Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserByUserName(String username) {
        return APP_USERS
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));
    }
}
