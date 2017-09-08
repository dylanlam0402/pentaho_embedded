package com.hellokoding.springboot.controller;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
@RequestMapping("/pentaho")
public class PentahoController {

    @RequestMapping
    public String redirectURL(HttpServletResponse response) throws IOException {

        return "pentaho";
    }



}
