package com.hellokoding.springboot.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class PentahoUser {
    private String userName;
    private String password;

    public PentahoUser() {
    }

    public PentahoUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject parseJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("password", password);
        return jsonObject;
    }
}
