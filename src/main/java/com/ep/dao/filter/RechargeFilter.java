package com.ep.dao.filter;

import com.ep.dao.model.common.PagingFilter;

/**
 * @auther fangchen.chai ON 2017/12/2
 */
public class RechargeFilter extends PagingFilter{

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
