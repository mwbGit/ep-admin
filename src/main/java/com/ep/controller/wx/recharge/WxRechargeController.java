package com.ep.controller.wx.recharge;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.wx.recharge.api.RechargeConfigResponse;
import com.ep.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther fangchen.chai ON 2017/12/12
 */
@Controller
@RequestMapping("/wx")
public class WxRechargeController {
    @Autowired
    private RechargeService rechargeService;

    @ResponseBody
    @RequestMapping("/recharge/configs")
    public ServiceResponse getRechargeConfigList(){
        RechargeConfigResponse response = new RechargeConfigResponse();
        response.setConfigs(rechargeService.getAllTConfigRecharge());
        return response;
    }
}
