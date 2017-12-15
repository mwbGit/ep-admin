package com.ep.controller.order;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.order.api.CreateOrderRequest;
import com.ep.controller.order.api.PreOrderResultResponse;
import com.ep.dao.model.common.PreOrderResult;
import com.ep.service.recharge.RechargeService;
import com.ep.service.we_chat.pay.api.WxPayConfig;
import com.ep.service.we_chat.pay.util.WechatXmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by fangchen.chai on 2017/8/20.
 */
@Controller
@RequestMapping(value = "/wx")
public class EparkeOrderController {

    Logger logger = LoggerFactory.getLogger(EparkeOrderController.class);

    @Autowired
    private WxPayConfig wxPayConfig;
    @Autowired
    private RechargeService rechargeService;

    @RequestMapping(value = "/order")
    @ResponseBody
    public ServiceResponse createOrder(@RequestBody CreateOrderRequest request){
        PreOrderResult result = null;
        PreOrderResultResponse resultResponse = new PreOrderResultResponse();
        try {

            result = rechargeService.createClientOrder(request.getTel(), request.getPay());
        } catch (Exception e) {
            resultResponse.setCode("1");
            resultResponse.setMessage(e.getMessage());
        }
        resultResponse.setResult(result);
        return resultResponse;
    }

    /**
     * 微信回调
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pay/we_chat/notify", method = {POST, GET})
    public void weChatPayNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.info("-------------------------------------- in wechat notify ------------------------------------------");
        try {

            Map<String, String> params = WechatXmlUtil.xmlToMap(request
                    .getInputStream());
            logger.info("params={}",params);
            String tradeStatus = params.get("result_code");
            if (tradeStatus != null && tradeStatus.equals("SUCCESS")) {
                //注意out_trade_no 是商户的订单号，因为商户对于微信是out。
                //transaction_id 是微信订单号，对于我们来说是outCode
                String outCode = params.get("transaction_id");
                String code = params.get("out_trade_no");
                rechargeService.modifyNotifyPayed(code, outCode);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("return_code", "SUCCESS");
            map.put("return_msg", "OK");
            response.getWriter().write(WechatXmlUtil.mapToXml(map));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }
    }
}
