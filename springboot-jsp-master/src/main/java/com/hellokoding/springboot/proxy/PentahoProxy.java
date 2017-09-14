package com.hellokoding.springboot.proxy;

import com.hellokoding.springboot.domain.User;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

/**
 * Created by kietlam on 9/11/2017.
 */

public interface PentahoProxy {
    public String getAllDashboardOfCurrentUser(User user);
    public String getCookieFromResponse();
}
