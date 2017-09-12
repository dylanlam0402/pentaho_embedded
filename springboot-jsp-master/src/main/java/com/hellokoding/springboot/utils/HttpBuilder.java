package com.hellokoding.springboot.utils;




import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by kietlam on 9/8/2017.
 */
public class HttpBuilder {


    public static String sendingGetRequest(String urlStr) throws Exception {

        //String urlString = "http://localhost:8080/JAXRSJsonCRUDExample/rest/countries";

        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // By default it is GET request
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Accept","application/json");
        con.setRequestProperty("Authorization","Basic YWRtaW46YWRtaW4=");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        return  response.toString();
    }
    public static String sendingPostRequest() throws Exception {
        return "";
    }
}
