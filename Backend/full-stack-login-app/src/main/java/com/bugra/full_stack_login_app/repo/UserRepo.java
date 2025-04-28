package com.bugra.full_stack_login_app.repo;

import com.bugra.full_stack_login_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

    User findUserByUsername(String username);
}
