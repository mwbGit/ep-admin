package com.ep.controller.wx.recharge.api;

import com.ep.controller.common.ServiceResponse;
import com.ep.dao.model.generated.TConfigRecharge;

import java.util.List;

/**
 * @auther fangchen.chai ON 2017/12/12
 */
public class RechargeConfigResponse extends ServiceResponse {

    private List<TConfigRecharge> configs;

    public List<TConfigRecharge> getConfigs() {
        return configs;
    }

    public void setConfigs(List<TConfigRecharge> configs) {
        this.configs = configs;
    }
}
