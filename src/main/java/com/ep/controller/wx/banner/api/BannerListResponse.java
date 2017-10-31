package com.ep.controller.wx.banner.api;

import java.util.List;

import com.ep.controller.common.ServiceResponse;

/**
 * Created by MengWeiBo on 2017-10-31
 */
public class BannerListResponse extends ServiceResponse {
    private List<BannerVO> data;

    public List<BannerVO> getData() {
        return data;
    }

    public void setData(List<BannerVO> data) {
        this.data = data;
    }
}
