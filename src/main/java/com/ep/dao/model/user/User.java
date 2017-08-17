package com.ep.dao.model.user;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private String password;
    private String remark;

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
}
