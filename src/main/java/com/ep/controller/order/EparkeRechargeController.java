package com.ep.controller.order;

import com.ep.controller.common.PagingResponse;
import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.service.recharge.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther fangchen.chai ON 2017/12/2
 */
@Controller
@RequestMapping("/recharge")
public class EparkeRechargeController {

    @Autowired
    private RechargeService rechargeService;

    @RequestMapping("/list")
    @ResponseBody
    public PagingResponse<List<TRechargeDetail>> getRechargeList(Integer iDisplayStart, Integer iDisplayLength, String sSearch, Integer typeId) {
        RechargeFilter filter = new RechargeFilter();

        return rechargeService.getTRechargeList(filter);
    }


}
