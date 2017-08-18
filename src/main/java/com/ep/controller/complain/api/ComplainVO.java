package com.ep.controller.complain.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.dao.model.complain.Complain;
import com.ep.util.DateTimeUtility;

/**
 * Created by MengWeiBo on 2017-08-18
 */
public class ComplainVO {
    private Integer id;
    private String type;
    private String itemName;
    private String dimensionName;
    private String dimensionType;
    private String content;
    private Integer grade;
    private String img;
    private String createTime;

    public static List<ComplainVO> toVOs(List<Complain> complains) {
        List<ComplainVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(complains)) {
            for (Complain complain : complains) {
                ComplainVO vo = new ComplainVO();
                vo.setId(complain.getId());
                vo.setItemName(complain.getDimension().getItem().getName());
                vo.setDimensionName(complain.getDimension().getName());
                vo.setDimensionType(complain.getDimension().getType().getDescription());
                vo.setContent(complain.getContent());
                vo.setGrade(complain.getGrade());
                vo.setImg(complain.getImg());
                vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(complain.getCreateTime()));

                vos.add(vo);
            }
        }

        return vos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}