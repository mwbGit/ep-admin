package com.ep.dao.model.activity;

import java.math.BigDecimal;
import java.util.Date;

import com.ep.dao.model.common.Bool;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class Activity {
    private Integer id;
    private String title;
    private Date startTime;
    private Date endTime;
    private Bool online;
    private BigDecimal price;
    private String img;
    private String content;
    private ActivityType type;
    private AddressDetail addressDetail;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Bool getOnline() {
        return online;
    }

    public void setOnline(Bool online) {
        this.online = online;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public AddressDetail getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(AddressDetail addressDetail) {
        this.addressDetail = addressDetail;
    }
}
