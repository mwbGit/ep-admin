package com.ep.controller.order;

import com.ep.controller.api.ServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangchen.chai on 2017/8/20.
 */
@Controller
@RequestMapping(value = "/e_parke")
public class EparkeOrderController {


    @RequestMapping(value = "/order")
    @ResponseBody
    public ServiceResponse createOrder(@RequestParam Integer  id) {

        return new ServiceResponse();
    }

}
