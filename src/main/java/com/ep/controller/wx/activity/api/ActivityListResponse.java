package com.ep.controller.wx.activity.api;

import java.util.List;

import com.ep.controller.common.ServiceResponse;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class ActivityListResponse extends ServiceResponse{
    private List<ActivityVO> data;

    public List<ActivityVO> getData() {
        return data;
    }

    public void setData(List<ActivityVO> data) {
        this.data = data;
    }
}
