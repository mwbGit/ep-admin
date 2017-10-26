package com.ep.dao.model.banner;

import java.util.Date;

import com.ep.dao.model.common.Bool;

/**
 * Created by MengWeiBo on 2017-10-23
 */
public class Banner {
    private Integer id;
    private String title;
    private String img;
    private String linkUrl;
    private Integer sequence;
    private Bool online;
    private Date updateDate;
    private Integer updatedById;
    private String updatedByName;
    private Integer activityId;
    private BannerType type;
    private BannerPosition position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Bool getOnline() {
        return online;
    }

    public void setOnline(Bool online) {
        this.online = online;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public BannerType getType() {
        return type;
    }

    public void setType(BannerType type) {
        this.type = type;
    }

    public BannerPosition getPosition() {
        return position;
    }

    public void setPosition(BannerPosition position) {
        this.position = position;
    }
}
