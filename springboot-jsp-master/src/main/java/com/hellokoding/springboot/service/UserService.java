package com.hellokoding.springboot.service;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by congle on 9/7/2017.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User signin(String email, String password) {
        User user = userRepo.findUserByEmail(email);

        // invalid email
        if (user == null) {
            return null;
        }

        // invalid password
        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}
