package com.ep.controller.community.api;

import com.ep.dao.model.common.Bool;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class CommunityInfoRequest {
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
    private List<Integer> userIds;
    private List<Integer> devices;
    private List<String> pictures;

    public Community toCommunity(){
        Community community = new Community();
        community.setId(this.id);
        community.setName(this.name);
        community.setTag(this.tag);
        community.setTips(this.tips);
        community.setContent(this.content);
        community.setStationTotal(this.stationTotal);
        community.setRentNum(this.rentNum);
        community.setSurplusNum(this.surplusNum);
        community.setMeetingNum(this.meetingNum);
        community.setActivityNum(this.activityNum);
        community.setPictures(this.pictures);

        return community;
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
