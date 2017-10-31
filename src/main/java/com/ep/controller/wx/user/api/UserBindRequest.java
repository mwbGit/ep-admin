package com.ep.controller.wx.user.api;

/**
 * Created by Admin on 2017/9/18.
 */
public class UserBindRequest {
    private String name;
    private String openCode;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
