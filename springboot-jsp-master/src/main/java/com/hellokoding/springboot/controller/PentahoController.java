package com.hellokoding.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
@RequestMapping("/pentaho")
public class PentahoController {

    @RequestMapping
    public String pentaho(){

        return "pentaho";
    }

}
