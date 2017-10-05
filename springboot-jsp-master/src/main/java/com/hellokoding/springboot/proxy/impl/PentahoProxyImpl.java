package com.hellokoding.springboot.proxy.impl;

import com.hellokoding.springboot.domain.User;
import com.hellokoding.springboot.proxy.PentahoProxy;
import com.hellokoding.springboot.utils.Constant;
import com.hellokoding.springboot.utils.RSADecryption;
import com.hellokoding.springboot.utils.SecurityContextHolder;
import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;

import java.net.URL;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.hellokoding.springboot.utils.Constant.COOKIES_HEADER;

/**
 * Created by kietlam on 9/11/2017.
 */
@Service
public class PentahoProxyImpl implements PentahoProxy {


    private HttpURLConnection initHttpConnection(String urlStr,User user, String passWord, String privateKeyPath, String publicKeyPath) throws Exception {
        HttpURLConnection urlConnection ;
        URL url = new URL(urlStr);
        urlConnection = (HttpURLConnection) url.openConnection();
        String userName = user.getEmail();

//        PublicKey publicKey = RSADecryption.getPemPublicKey(publicKeyPath, "RSA");
//        String encrypt = RSADecryption.encrypt("abc", publicKey);



        PrivateKey privateKey = RSADecryption.getPemPrivateKey(privateKeyPath,"RSA");
//        String decrypt = RSADecryption.decrypt(encrypt,privateKey);

        String validPassword = RSADecryption.decrypt(passWord,privateKey);
        String authorization = userName+":"+validPassword;
        byte[] encodedBytes = Base64.encodeBase64(authorization.getBytes());
        String validAuthorization = new String(encodedBytes);
        String authorizarionHeader = "Basic "+validAuthorization;
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setRequestProperty("Authorization",authorizarionHeader);
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
    public String getAllDashboardOfCurrentUser(User user, String passWord, String privateKeyPath, String publicKeyPath) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = initHttpConnection(Constant.ALL_DASHBOARD_API, user, passWord, privateKeyPath, publicKeyPath);
            urlConnection.setRequestMethod("GET");
        } catch (Exception e) {
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
    public CookieManager getCookieFromResponse(User user, String passWord, String privateKeyPath, String publicKeyPath) {
        HttpURLConnection urlConnection = null;
        CookieManager cookieManager = new CookieManager();
        try {
            urlConnection = initHttpConnection(Constant.ALL_DASHBOARD_API, user, passWord, privateKeyPath, publicKeyPath);
            urlConnection.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get("Set-Cookie");
        if(cookiesHeader != null){
            for(String header : cookiesHeader){
                HttpCookie httpCookie = HttpCookie.parse(header).get(0);
                cookieManager.getCookieStore().add(null, httpCookie);
            }
        }

        return cookieManager;
    }

}
