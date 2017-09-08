package com.hellokoding.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kietlam on 9/7/2017.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

}
