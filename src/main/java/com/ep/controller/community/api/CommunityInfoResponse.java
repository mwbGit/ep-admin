package com.ep.controller.community.api;

import com.ep.controller.common.ServiceResponse;

import java.math.BigDecimal;
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
    private Integer stationTotal;
    private Integer rentNum;
    private Integer surplusNum;
    private List<Integer> userIds;
    private List<Integer> devices;
    private List<String> pictures;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

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

    public Integer getStationTotal() {
        return stationTotal;
    }

    public void setStationTotal(Integer stationTotal) {
        this.stationTotal = stationTotal;
    }

    public Integer getRentNum() {
        return rentNum;
    }

    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getDevices() {
        return devices;
    }

    public void setDevices(List<Integer> devices) {
        this.devices = devices;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
