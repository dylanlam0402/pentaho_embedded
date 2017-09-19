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

import com.hellokoding.springboot.utils.EncryptionUtil;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
@RequestMapping("/pentaho")
public class PentahoController extends  BaseController {

    public static final String[] FILE_NOT_READ = {};

    @Autowired
    PentahoProxy pentahoProxy;

    @Autowired
    UserRepository userRepository;



    @RequestMapping
    public String redirectURL(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException, JSONException {

        HttpSession session = request.getSession(true);
        String email = (String)session.getAttribute("email");
        User user = userRepository.findUserByEmail(email);
        pentahoProxy= new PentahoProxyImpl();

        CookieManager cookieManager = pentahoProxy.getCookieFromResponse(user);
        CookieStore cookieStore = cookieManager.getCookieStore();
        List<HttpCookie> cookies = cookieStore.getCookies();
        for (HttpCookie cookie : cookies){
            Cookie cookieLogin = new Cookie(cookie.getName(), cookie.getValue());
            cookieLogin.setPath(cookie.getPath());
            cookieLogin.setSecure(cookie.getSecure());
            cookieLogin.setMaxAge((int) cookie.getMaxAge());
            response.addCookie(cookieLogin);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = pentahoProxy.getAllDashboardOfCurrentUser(user);
        Children children  = mapper.readValue(jsonStr,Children.class);
        children.printTree(children);
        List<File> allFilesAndFolders = children.allFilesAndFolders;
        List<File> fileNotFolder = new ArrayList<>();
        for(File file : allFilesAndFolders){
            if (file.getFolder().equals("false")){
                fileNotFolder.add(file);
            }
        }

        List<File> listDashboard = new ArrayList<>();
        for (File file : fileNotFolder){
            if (file.getPath().contains(".wcdf")){
                listDashboard.add(file);
            }
        }

        model.addAttribute("listDashboard", listDashboard);

        return "pentaho";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getDashboard(@PathVariable("id") String id, Model model ) {
        List<File> allFilesAndFolders = Children.allFilesAndFolders;
        for (File file : allFilesAndFolders){
            if (file.getId().equals(id)){
                String pathFile = file.getPath();
                String pathReport = pathFile.replace("/","%3A");
                model.addAttribute("pathReport",pathReport);

            }
        }
        return "report";
    }




}
