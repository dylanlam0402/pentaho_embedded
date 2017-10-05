package com.hellokoding.springboot.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellokoding.springboot.domain.Children;
import com.hellokoding.springboot.domain.File;
import com.hellokoding.springboot.domain.PentahoUser;
import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.proxy.impl.PentahoProxyImpl;
import com.hellokoding.springboot.repository.UserRepository;

import com.hellokoding.springboot.utils.Constant;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;



/**
 * Created by kietlam on 9/8/2017.
 */
@Controller
public class PentahoController extends  BaseController {

    @Autowired
    PentahoProxy pentahoProxy;

    @Autowired
    UserRepository userRepository;

    @Value("${password-pentaho}")
    private String passWord ;

    @Value("${private-key-path}")
    private String privateKeyPath ;

    @Value("${public-key-path}")
    private String publicKeyPath ;

    @RequestMapping(value = "/pentaho",method = RequestMethod.GET)
    public ModelAndView redirectURL( HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {

        HttpSession session = request.getSession(true);
        String email = (String)session.getAttribute("email");
        User user = userRepository.findUserByEmail(email);
        pentahoProxy= new PentahoProxyImpl();

        CookieManager cookieManager = pentahoProxy.getCookieFromResponse(user, passWord, privateKeyPath, publicKeyPath);
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
        String jsonStr = pentahoProxy.getAllDashboardOfCurrentUser(user, passWord, privateKeyPath, publicKeyPath);
        Children children  = mapper.readValue(jsonStr,Children.class);
        if(children.allFilesAndFolders.size()==0){
            children.printTree(children);
        }

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
        ModelAndView mv = new ModelAndView("pentaho");
        mv.addObject("listDashboard",listDashboard);
        return mv;
    }

    @RequestMapping(value="/pentaho/{id}", method= RequestMethod.GET)
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

    @RequestMapping(value="/pentaho/create", method= RequestMethod.GET)
    public String createUser(Model model, HttpServletResponse response, HttpServletRequest request ) throws JSONException, UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        PentahoUser user = new PentahoUser("tuando","1");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userName","tuando");
//        jsonObject.put("password","1");
        JSONObject jsonObject = user.parseJson();


        byte[] xApiAuthTokenBytes = String.join(":","admin","Kms@123").getBytes("utf-8");
        String xApiAuthToken = Base64.getEncoder().encodeToString(xApiAuthTokenBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + xApiAuthToken);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);

        ResponseEntity<String> loginResponse = restTemplate.exchange(Constant.CREATE_USER, HttpMethod.PUT, entity, String.class);

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
           model.addAttribute("Create User Success","success");
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // nono... bad credentials
        }

        return "test";
    }

}
