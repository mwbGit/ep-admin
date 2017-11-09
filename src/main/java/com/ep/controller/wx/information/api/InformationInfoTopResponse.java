package com.ep.controller.wx.information.api;

import com.ep.controller.common.ServiceResponse;

import java.util.List;

/**
 * Created by 吴晓海 on 2017/11/9.
 */
public class InformationInfoTopResponse extends ServiceResponse {
    private List<InformationVO> data;

    public List<InformationVO> getData() {
        return data;
    }

    public void setData(List<InformationVO> data) {
        this.data = data;
    }

}
