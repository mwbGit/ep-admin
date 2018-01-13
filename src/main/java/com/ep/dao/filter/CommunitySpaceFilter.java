package com.ep.dao.filter;

import com.ep.dao.model.common.PagingFilter;

/**
 * Created by Admin on 2017/9/21.
 */
public class CommunitySpaceFilter extends PagingFilter {
    private Integer type;
    private Integer communityId;
    private Boolean online;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}

