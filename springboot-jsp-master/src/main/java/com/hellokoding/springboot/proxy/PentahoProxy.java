package com.hellokoding.springboot.proxy;

import com.hellokoding.springboot.domain.User;

import java.net.CookieManager;

/**
 * Created by kietlam on 9/11/2017.
 */

public interface PentahoProxy {
    public String getAllDashboardOfCurrentUser(User user, String passWord, String privateKeyPath, String publicKeyPath);
    public CookieManager getCookieFromResponse(User user, String passWord, String privateKeyPath, String publicKeyPath);
}
