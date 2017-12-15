package com.ep.controller.wx.activity.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.dao.model.activity.Activity;
import com.ep.util.DateTimeUtility;

/**
 * Created by MengWeiBo on 2017-10-13
 */
public class ActivityVO {
    private Integer id;
    private String title;
    private String img;
    private String startTime;
    private String endTime;
    private BigDecimal price;
    private String typeName;
    private String status;

    public static List<ActivityVO> toVOs(List<Activity> activities) {
        List<ActivityVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(activities)) {
            for (Activity activity : activities) {
                ActivityVO vo = new ActivityVO();
                vo.setId(activity.getId());
                vo.setTitle(activity.getTitle());
                vo.setImg(activity.getImg());
                vo.setPrice(activity.getPrice());
                vo.setStartTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getStartTime()));
                vo.setEndTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getEndTime()));
                vo.setTypeName(activity.getType().getName());
                Date now = new Date();
                if (now.before(activity.getEndTime()) && now.after(activity.getStartTime())) {
                    vo.setStatus("进行中");
                } else if (now.after(activity.getEndTime())) {
                    vo.setStatus("已结束");
                } else {
                    vo.setStatus("未开始");
                }


                vos.add(vo);
            }
        }

        return vos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
