package com.ep.controller.wx.community.api;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.community.api.CommunityDeviceVO;
import com.ep.controller.user.api.UserVO;
import com.ep.dao.model.community.Device;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class CommunityInfoResponse extends ServiceResponse {
    private Integer id;
    private String name;
    private String tag;
    private String tips;
    private String content;
    private List<UserVO> users;
    private List<CommunityDeviceVO> devices;
    private List<String> pictures;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }

    public List<CommunityDeviceVO> getDevices() {
        return devices;
    }

    public void setDevices(List<CommunityDeviceVO> devices) {
        this.devices = devices;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
