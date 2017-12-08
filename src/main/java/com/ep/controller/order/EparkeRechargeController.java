package com.ep.controller.order;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.dao.filter.ConfigRechargeFilter;
import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.model.generated.TConfigRecharge;
import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther fangchen.chai ON 2017/12/2
 */
@Controller
@RequestMapping(value = "/recharge")
public class EparkeRechargeController {

    @Autowired
    private RechargeService rechargeService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse<List<TRechargeDetail>> getRechargeList(Integer iDisplayStart, Integer iDisplayLength) {
        RechargeFilter filter = new RechargeFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        return rechargeService.getTRechargeList(filter);
    }

    @RequestMapping(value = "/config/add")
    @ResponseBody
    public ServiceResponse createRechargeConfig(@RequestBody TConfigRecharge configRecharge) {

        return rechargeService.addConfigRecharge(configRecharge);
    }

    @RequestMapping(value = "/config/delete")
    @ResponseBody
    public ServiceResponse deleteRechargeConfig(@RequestParam Integer id) {
        rechargeService.deleteConfigRecharge(id);
        return new ServiceResponse();
    }

    @RequestMapping(value = "/config/update")
    @ResponseBody
    public ServiceResponse updateRechargeConfig(@RequestBody TConfigRecharge configRecharge) {
        return rechargeService.updateConfigRecharge(configRecharge);
    }

    @RequestMapping(value = "/config/list")
    @ResponseBody
    public PagingResponse<List<TConfigRecharge>> getRechargeConfigList(Integer iDisplayStart, Integer iDisplayLength) {
        ConfigRechargeFilter filter = new ConfigRechargeFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        return rechargeService.selectConfigRechargeByFilter(filter);
    }

}
