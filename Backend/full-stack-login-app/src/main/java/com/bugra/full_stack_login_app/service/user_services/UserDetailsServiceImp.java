package com.bugra.full_stack_login_app.service.user_services;

import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.responses.UserResponseMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImp implements UserDetailsService {


    private final UserService userService;

    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException(UserResponseMessage.USER_NOT_FOUND + " " +username);
        }

        return new UserPrincipal(user);
    }
}
