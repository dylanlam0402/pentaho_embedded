package com.hellokoding.springboot.service;



import com.hellokoding.springboot.domain.ListDashboardDTO;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.utils.HttpBuilder;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by kietlam on 9/8/2017.
 */
public class PentahoService {


    public static ListDashboardDTO[] excutePost() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Basic YWRtaW46YWRtaW4=");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<ListDashboardDTO[]> responseEntit= restTemplate.exchange("http://localhost:8080/pentaho/api/repo/files/%3Ahome/children",HttpMethod.GET, entity, ListDashboardDTO[].class);

        return responseEntit.getBody();
    }
}
