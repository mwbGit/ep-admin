package com.ep.controller.wx.user;

/**
 * Created by Admin on 2017/9/18.
 */
public class UserBindRequest {
    private String openId;
    private String mobile;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
