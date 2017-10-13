package com.ep.controller.activity.api;

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
    private Boolean online;
    private String typeName;
    private String status;
    private String timeRange;

    public static List<ActivityVO> toVOs(List<Activity> activities) {
        List<ActivityVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(activities)) {
            for (Activity activity : activities) {
                ActivityVO vo = new ActivityVO();
                vo.setId(activity.getId());
                vo.setTitle(activity.getTitle());
                vo.setOnline(activity.getOnline().getValue());
                vo.setTypeName(activity.getType().getName());
                Date now = new Date();
                if (now.before(activity.getEndTime()) && now.after(activity.getStartTime())) {
                    vo.setStatus("进行中");
                } else if (now.after(activity.getEndTime())) {
                    vo.setStatus("已结束");
                } else {
                    vo.setStatus("未开始");
                }

                vo.setTimeRange(DateTimeUtility.formatYYYYMMDDHHMM(activity.getStartTime())
                        + " - " + DateTimeUtility.formatYYYYMMDDHHMM(activity.getEndTime()));

                vos.add(vo);
            }
        }

        return vos;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
}
