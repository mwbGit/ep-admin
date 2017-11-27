package com.ep.controller.epark;

import com.ep.controller.epark.api.BalanceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.user.User;
import com.ep.service.epclient.EParkeClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther fangchen.chai ON 2017/11/27
 */
@Controller
@RequestMapping("/wx")
public class EParkClientController {


    @RequestMapping(value = "/balance")
    @ResponseBody
    public BalanceResponse getBalance() {
        Double balance = null;
        BalanceResponse response = new BalanceResponse();
        try {
            User user = ApplicationContextUtils.getUser();
            balance = EParkeClient.getBalance(user.getMobile());
//            balance = EParkeClient.getBalance("18511332532");
        } catch (Exception e) {
            response.setCode("5000");
            response.setMessage("获取用户余额失败！");
        }
        response.setBalance(balance);
       return response;
    }
}
