package com.ep.dao.model.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ep.dao.model.common.Bool;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private String company;
    private String sex;
    private String mobile;
    private String password;
    private String remark;
    private Date createDate;
    private Date updateDate;
    private Integer updatedById;
    private String updatedByName;
    private Bool deleted;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Bool getDeleted() {
        return deleted;
    }

    public void setDeleted(Bool deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
