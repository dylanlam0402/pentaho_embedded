package com.hellokoding.springboot.controller;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.service.UserService;
import com.hellokoding.springboot.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by congle on 9/7/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                        HttpServletRequest request,Model model ) {
        String validPassword = EncryptionUtil.toMD5(password);
        User user = userService.signin(userName, validPassword);

        if (user == null){
            return "login";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("email",user.getEmail());
        session.setMaxInactiveInterval(3*60);
        model.addAttribute("username", user.getFirstName());
        return "index";
    }

}
