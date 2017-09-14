package com.hellokoding.springboot.proxy.impl;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.utils.Constant;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kietlam on 9/11/2017.
 */
@Service
public class PentahoProxyImpl implements PentahoProxy {

    private HttpURLConnection initHttpConnection(String urlStr,User user) throws IOException {
        HttpURLConnection urlConnection ;
        URL url = new URL(urlStr);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestProperty("Authorization","Basic YWRtaW46cGFzc3dvcmQ=");
        return urlConnection;

    }

    private void readResponse(HttpURLConnection urlConnection,StringBuffer responseBody) throws IOException {
        String output;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        while ((output = in.readLine()) != null) {
            responseBody.append(output);
        }

        in.close();
    }

    @Override
    public String getAllDashboardOfCurrentUser(User user) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = initHttpConnection(Constant.ALL_DASHBOARD_API, user);
            urlConnection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer response = new StringBuffer();
        try {
            readResponse(urlConnection,response);
        } catch (IOException e) {

        }
        return response.toString();
    }

    @Override
    public String getCookieFromResponse() {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = initHttpConnection(Constant.ALL_DASHBOARD_API, null);
            urlConnection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
        String cookie = "";
        while (hearerFieldsIter.hasNext()) {
            String headerFieldKey = hearerFieldsIter.next();
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
                for (String headerValue : headerFieldValue) {
                    String[] fields = headerValue.split(";\\s*");
                    String cookieValue = fields[0];
                    cookie = cookieValue;
                }
            }
        }
        return cookie;
    }

}
