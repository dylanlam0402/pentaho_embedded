package com.hellokoding.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kietlam on 9/7/2017.
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @RequestMapping
    public String home(Model model,@RequestParam(required=false) String username) {
        model.addAttribute("username",username);
        return "home";
    }

}
