package com.ep.controller.wx.activity.api;

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
    private String img;
    private String title;

    public static List<ActivityVO> toVOs(List<Activity> activities) {
        List<ActivityVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(activities)) {
            for (Activity activity : activities) {
                ActivityVO vo = new ActivityVO();
                vo.setId(activity.getId());
                vo.setTitle(activity.getTitle());
                vo.setImg(activity.getImg());

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
