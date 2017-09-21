package com.ep.dao.model.complain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MengWeiBo on 2017-08-18
 */
public class Complain  implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String content;
    private Integer grade;
    private Date createTime;
    private String img;
    private String dimensionId;
    private Dimension dimension;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }
}
