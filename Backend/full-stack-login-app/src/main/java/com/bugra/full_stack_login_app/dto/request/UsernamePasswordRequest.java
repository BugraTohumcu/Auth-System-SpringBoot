package com.bugra.full_stack_login_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordRequest {
    private String username;
    private String password;
}
