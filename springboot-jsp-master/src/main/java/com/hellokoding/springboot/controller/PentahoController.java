package com.hellokoding.springboot.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hellokoding.springboot.domain.Children;
import com.hellokoding.springboot.domain.ChildrenDeserializer;
import com.hellokoding.springboot.domain.File;
import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.proxy.impl.PentahoProxyImpl;
import com.hellokoding.springboot.repository.UserRepository;

import com.hellokoding.springboot.utils.ItemDeserializer;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
@RequestMapping("/pentaho")
public class PentahoController extends  BaseController {

    @Autowired
    PentahoProxy pentahoProxy;

    @Autowired
    UserRepository userRepository;

    @RequestMapping
    public String redirectURL(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException, JSONException {

        String a = null;
        HttpSession session = request.getSession(true);
        String email = (String)session.getAttribute("email");
        User user = userRepository.findUserByEmail(email);
        pentahoProxy= new PentahoProxyImpl();

        String jsonStr = pentahoProxy.getAllDashboardOfCurrentUser(user);



//        JSONObject jsonObject = new JSONObject(jsonStr);
//        JSONArray jsonArray = jsonObject.getJSONArray("children");
//
//        ObjectMapper objectMapper= new ObjectMapper();
//
//        JSONObject homeFolder = jsonArray.getJSONObject(0);
//
//        JSONArray homeChild =  homeFolder.getJSONArray("children");


//        for (int i = 0; i< homeChild.length(); i++){
//            String path = homeChild.getJSONObject(i).getString("file");
//        }



//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Children.class, new ChildrenDeserializer());
//        mapper.registerModule(module);
//
//        Children readValue = mapper.readValue(homeChild.toString(), Children.class);

//        Children children = objectMapper.readValue(homeChild.toString(),Children.class);
 //       JsonNode node = objectMapper.readTree(jsonStr).get("children");
//        JsonNode nodeDau = node.get(0);




//        String cookieValue = pentahoProxy.getCookieFromResponse();
//        Cookie cookie = new Cookie("cookieValue", cookieValue);
//        response.addCookie(cookie);
        return "pentaho";
    }



}
