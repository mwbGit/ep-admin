package com.ep.controller.wx.banner.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.controller.common.ServiceResponse;
import com.ep.dao.model.banner.Banner;

/**
 * Created by MengWeiBo on 2017-10-31
 */
public class BannerVO{
    private Integer id;
    private String img;
    private String linkUrl;
    private Integer activityId;
    private String typeCode;
    private String typeName;

    public static List<BannerVO> toVOs(List<Banner> list) {
        List<BannerVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Banner banner : list) {
                BannerVO vo = new BannerVO();
                vo.setId(banner.getId());
                vo.setImg(banner.getImg());
                vo.setLinkUrl(banner.getLinkUrl());
                vo.setTypeCode(banner.getType().getCode());
                vo.setTypeName(banner.getType().getDescription());
                vo.setActivityId(banner.getActivityId());

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
