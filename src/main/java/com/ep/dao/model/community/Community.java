package com.ep.dao.model.community;

import com.ep.dao.model.common.Bool;
import com.ep.dao.model.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 社区
 * Created by mengweibo on 2017/12/18.
 */
public class Community implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private String tag;
    private String tips;
    private String content;
    private Integer stationTotal;
    private Integer rentNum;
    private Integer surplusNum;
    private Integer meetingNum;
    private Integer activityNum;
    private Bool online;
    private Date createdDate;
    private Date updatedDate;
    private Integer updatedById;
    private String updatedByName;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private List<User> users;
    private List<Device> devices;
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

    public Integer getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(Integer meetingNum) {
        this.meetingNum = meetingNum;
    }

    public Integer getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(Integer activityNum) {
        this.activityNum = activityNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public Bool getOnline() {
        return online;
    }

    public void setOnline(Bool online) {
        this.online = online;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

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
}