package com.hellokoding.springboot.service;


import com.hellokoding.springboot.utils.Constant;
import com.hellokoding.springboot.utils.HttpBuilder;

import java.net.*;
import java.io.*;




/**
 * Created by kietlam on 9/8/2017.
 */
public class PentahoService {

    public static CookieManager excutePost() throws IOException {
        CookieManager cookieManager = HttpBuilder.getCookieFromResponse(Constant.AUTHENTICATION_API);
        return
    }
}
