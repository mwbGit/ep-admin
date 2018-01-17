package com.ep.controller.wx.community.api;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.community.api.CommunityDeviceVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class ActivityMeetingSpaceInfoResponse extends ServiceResponse {
    private Integer id;
    private String name;
    private String position;
    private String img;
    private Integer capacity;
    private BigDecimal amount;
    private List<CommunityDeviceVO> devices;
    private Integer communityId;
    private String communityName;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<CommunityDeviceVO> getDevices() {
        return devices;
    }

    public void setDevices(List<CommunityDeviceVO> devices) {
        this.devices = devices;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
