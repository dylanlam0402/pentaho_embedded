package com.hellokoding.springboot.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.net.*;

import java.util.List;
import java.util.Map;

/**
 * Created by kietlam on 9/8/2017.
 */
public class HttpBuilder {


    public static CookieManager getCookieFromResponse(String targetURL, String urlParameters) throws IOException {
        HttpURLConnection connection = null;
        CookieManager cookieManager = new CookieManager();
        URL url = new URL(targetURL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length",
                Integer.toString(urlParameters.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        String token= "Basic "+ new String(new Base64().encode(urlParameters.getBytes()));
        connection.setRequestProperty("Authorization",token);
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        Map<String, List<String>> headerFields = connection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(Constant.COOKIES_HEADER);

        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
        }
        return cookieManager;
    }
}
