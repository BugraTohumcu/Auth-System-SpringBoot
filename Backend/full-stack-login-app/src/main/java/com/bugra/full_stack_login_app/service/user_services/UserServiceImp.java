package com.bugra.full_stack_login_app.service.user_services;

import com.bugra.full_stack_login_app.dto.ResponseMessage;
import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.repo.UserRepo;
import com.bugra.full_stack_login_app.dto.request.UsernamePasswordRequest;
import com.bugra.full_stack_login_app.responses.UserResponseMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{


    private final UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getByUserName(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    public ResponseMessage saveNewUser(UsernamePasswordRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

        if(userRepo.findUserByUsername(request.getUsername()) != null){
            return new ResponseMessage(UserResponseMessage.USERNAME_EXIST,false);
        }
        userRepo.save(newUser);
        return new ResponseMessage(UserResponseMessage.USER_SAVED,true);
    }
}
