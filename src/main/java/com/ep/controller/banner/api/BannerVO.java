package com.ep.controller.banner.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.dao.model.banner.Banner;
import com.ep.util.DateTimeUtility;

/**
 * Created by MengWeiBo on 2017-10-23
 */
public class BannerVO {
    private Integer id;
    private String title;
    private String img;
    private String linkUrl;
    private Integer sequence;
    private Boolean online;
    private String updateDate;
    private String updatedByName;
    private String typeCode;
    private String typeName;
    private Integer activityId;
    private Boolean first;
    private Boolean end;

    public static List<BannerVO> toVOs(List<Banner> list) {
        List<BannerVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Banner banner = list.get(i);
                BannerVO vo = new BannerVO();
                vo.setId(banner.getId());
                vo.setTitle(banner.getTitle());
                vo.setImg(banner.getImg());
                vo.setLinkUrl(banner.getLinkUrl());
                vo.setSequence(banner.getSequence());
                vo.setOnline(banner.getOnline().getValue());
                vo.setUpdateDate(DateTimeUtility.formatYYYYMMDD(banner.getUpdateDate()));
                vo.setUpdatedByName(banner.getUpdatedByName());
                vo.setTypeCode(banner.getType().getCode());
                vo.setTypeName(banner.getType().getDescription());
                vo.setActivityId(banner.getActivityId());

                if (i == 0) {
                    vo.setFirst(true);
                } else {
                    vo.setFirst(false);
                }
                if (i == list.size() - 1) {
                    vo.setEnd(true);
                } else {
                    vo.setEnd(false);
                }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
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

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
