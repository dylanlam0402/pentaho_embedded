package com.hellokoding.springboot.proxy.impl;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.utils.Constant;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

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
        urlConnection.setRequestProperty("Authorization","Basic YWRtaW46YWRtaW4=");
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
    public String getAllDashboardOfCurrentUser() {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = initHttpConnection(Constant.ALL_DASHBOARD_API, null);
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
}
