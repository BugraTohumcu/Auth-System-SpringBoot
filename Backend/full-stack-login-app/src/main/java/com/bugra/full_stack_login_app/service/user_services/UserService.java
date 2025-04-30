package com.bugra.full_stack_login_app.service.user_services;

import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.request.UsernamePasswordRequest;


public interface UserService {
    User getByUserName(String username);
    String saveNewUser(UsernamePasswordRequest request);
}
