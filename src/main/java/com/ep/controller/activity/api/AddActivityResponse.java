package com.ep.controller.activity.api;

import java.math.BigDecimal;

import com.ep.controller.common.ServiceResponse;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class AddActivityResponse extends ServiceResponse{
    private Integer id;
    private String title;
    private String img;
    private String startTime;
    private String endTime;
    private BigDecimal price;
    private String content;
    private Integer addressId;
    private Integer addressDetailId;
    private Integer typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Integer getAddressDetailId() {
        return addressDetailId;
    }

    public void setAddressDetailId(Integer addressDetailId) {
        this.addressDetailId = addressDetailId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
