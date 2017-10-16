package com.ep.dao.filter;

import com.ep.dao.model.common.PagingFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Admin on 2017/9/21.
 */
public class ActivityUserFilter extends PagingFilter {
    private Integer activityId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (StringUtils.isNotBlank(userName)) {
            this.userName = "%" + userName + "%";
        }
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
