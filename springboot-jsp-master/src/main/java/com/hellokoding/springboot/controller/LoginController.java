package com.hellokoding.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by congle on 9/7/2017.
 */
@Controller
public class LoginController {

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(@RequestParam("userName") String username, @RequestParam("password") String password) {
        return "login";
    }

}
