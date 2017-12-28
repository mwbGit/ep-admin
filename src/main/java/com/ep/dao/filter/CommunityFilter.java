package com.ep.dao.filter;

import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;

/**
 * Created by Admin on 2017/9/21.
 */
public class CommunityFilter extends PagingFilter {
    private Bool online;

    public Bool getOnline() {
        return online;
    }

    public void setOnline(Bool online) {
        this.online = online;
    }
}

