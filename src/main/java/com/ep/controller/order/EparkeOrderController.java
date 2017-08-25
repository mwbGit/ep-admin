package com.ep.controller.order;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.order.api.CreateOrderRequest;
import com.ep.service.we_chat.pay.api.WxPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangchen.chai on 2017/8/20.
 */
@Controller
@RequestMapping(value = "/e_parke")
public class EparkeOrderController {

    @Autowired
    private WxPayConfig wxPayConfig;

    @RequestMapping(value = "/order")
    @ResponseBody
    public ServiceResponse createOrder(@RequestBody CreateOrderRequest request) {

        return new ServiceResponse();
    }

}
