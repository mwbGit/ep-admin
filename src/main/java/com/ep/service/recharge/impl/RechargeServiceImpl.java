package com.ep.service.recharge.impl;

import com.ep.dao.mapper.TRechargeDetailMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PreOrderResult;
import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.dao.model.user.User;
import com.ep.service.epclient.EParkeClient;
import com.ep.service.recharge.RechargeService;
import com.ep.service.we_chat.pay.WeChatPayService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fangchen.chai on 2017/8/27
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    private Log logger = LogFactory.getLog(RechargeServiceImpl.class);

    @Value("${wxpay.notifyUrl}")
    private String WX_PAY_NOTIFY_URL;
    @Autowired
    private TRechargeDetailMapper rechargeDetailMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatPayService weChatPayService;


    @Override
    public PreOrderResult createClientOrder(String tel, Float moneySum) {
        Boolean result = EParkeClient.checkPhone(tel);
        if (!result) {
            throw new RuntimeException("不能给该手机号充值");
        }
        TRechargeDetail detail = createOrder(tel, moneySum);
        PreOrderResult preOrderResult = weChatPayService.getWechatPreOrderResult(detail.getUserId(), moneySum, detail.getSysOrder(), WX_PAY_NOTIFY_URL);
        return preOrderResult;
    }

    @Override
    public Boolean notifyPayed(String order, String outOrder) {
        Long id = rechargeDetailMapper.selectOrderIdByOrder(order);
        if (id == null) {
            logger.error("订单回调时发现我方单号不存在，返回值为：我方单号：" + order + " 对方单号：" + outOrder);
            return false;
        }
        TRechargeDetail detail = rechargeDetailMapper.selectByPrimaryKey(id);
        setOrderPayed(order, outOrder);
        Boolean result =  EParkeClient.topUp(detail.getTel(), detail.getTel(), new Date(), detail.getRechargeAmount().doubleValue());
        return result;
    }

    private void setOrderPayed(String orderCode, String outOrderCode) {
        Long orderId = rechargeDetailMapper.selectOrderIdByOrder(orderCode);
        if (orderId == null) {
            throw new RuntimeException("订单号:" + orderId + "不存在");
        }
        if (outOrderCode != null) {
            rechargeDetailMapper.setOutPayCodeById(outOrderCode, orderId);
            rechargeDetailMapper.setPayedById(orderId);
        }

    }

    private TRechargeDetail createOrder(String tel, Float moneySum) {
        if (moneySum <= 0) {
            throw new RuntimeException("不能充值负数");
        }
        User user = userMapper.selectUserByMobile(tel);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        TRechargeDetail detail = new TRechargeDetail();
        detail.setUserName(user.getName());
        detail.setSysOrder(createOrderCode());
        detail.setRechargeAmount(moneySum);
        detail.setTel(user.getMobile());
        detail.setUserId(user.getId());
        rechargeDetailMapper.insertSelective(detail);
        return detail;
    }

    private String createOrderCode() {
        String pix = String.valueOf(System.nanoTime());
        return pix + RandomStringUtils.random(8, "1234567890");
    }

}
