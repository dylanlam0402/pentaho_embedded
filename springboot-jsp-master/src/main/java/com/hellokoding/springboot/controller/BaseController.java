package com.hellokoding.springboot.controller;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by kietlam on 9/8/2017.
 */
public abstract class BaseController {
    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return SecurityContextHolder.getCurrentUser();
    }

}
