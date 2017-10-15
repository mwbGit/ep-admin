package com.ep.controller.activity.api;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-10-13
 */
public class ActivityUserVO {
    private Integer id;
    private Integer orderId;
    private BigDecimal price;
    private String createDate;
    private String userSpaces;
    private String name;
    private String company;
    private String sex;
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUserSpaces() {
        return userSpaces;
    }

    public void setUserSpaces(String userSpaces) {
        this.userSpaces = userSpaces;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
