package com.ep.controller.information.api;

import java.util.Date;

/**
 * Created by 吴晓海 on 2017/10/24.
 */
public class InformationVO {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String createTime;



    private String content;
}
