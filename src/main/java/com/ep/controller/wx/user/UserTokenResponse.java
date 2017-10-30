package com.ep.controller.wx.user;

import com.ep.controller.common.ServiceResponse;

/**
 * Created by Admin on 2017/9/18.
 */
public class UserTokenResponse extends ServiceResponse{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
