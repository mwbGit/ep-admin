package com.ep.controller.wx.recharge;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.controller.wx.recharge.api.RechargeConfigResponse;
import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.dao.model.user.User;
import com.ep.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public ServiceResponse getRechargeConfigList() {
        RechargeConfigResponse response = new RechargeConfigResponse();
        response.setConfigs(rechargeService.getAllTConfigRecharge());
        return response;
    }

    @ResponseBody
    @RequestMapping("/recharge/detail")
    public PagingResponse<List<TRechargeDetail>> getRechargeDetail(Integer iDisplayStart, Integer iDisplayLength){
        RechargeFilter filter = new RechargeFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        User user = ApplicationContextUtils.getUser();
        filter.setUserId(user.getId());
        return rechargeService.getTRechargeList(filter);
    }
}
