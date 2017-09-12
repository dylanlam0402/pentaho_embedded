package com.hellokoding.springboot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellokoding.springboot.domain.ListDashboardDTO;
import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.proxy.impl.PentahoProxyImpl;
import com.hellokoding.springboot.service.PentahoService;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
@RequestMapping("/pentaho")
public class PentahoController {

    @Autowired
    PentahoProxy pentahoProxy;

    @RequestMapping
    public String redirectURL(HttpServletResponse response) throws IOException, JSONException {
        pentahoProxy= new PentahoProxyImpl();
        String jsonStr = pentahoProxy.getAllDashboardOfCurrentUser();
        ObjectMapper objectMapper= new ObjectMapper();
        ListDashboardDTO listDashboardDTO =  objectMapper.readValue(jsonStr,ListDashboardDTO.class);
        String a = null;
        return "pentaho";
    }



}
