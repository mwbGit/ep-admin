package com.ep.dao.model.advice;

import com.ep.dao.model.common.Bool;

import java.io.Serializable;
import java.util.Date;

public class Advice  {
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

    public String getMiniText() {
        return miniText;
    }

    public void setMiniText(String miniText) {
        this.miniText = miniText;
    }



    public String getImgUpload() {
        return imgUpload;
    }

    public void setImgUpload(String imgUpload) {
        this.imgUpload = imgUpload;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Integer id;
    private String title;
    private String miniText;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }


    private String imgUpload;
    private String typeId;
    private Date createTime;



    private String content;
    }