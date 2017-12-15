package com.ep.service.recharge.impl;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.filter.ConfigRechargeFilter;
import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.mapper.TConfigRechargeMapper;
import com.ep.dao.mapper.TRechargeDetailMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.PreOrderResult;
import com.ep.dao.model.generated.TConfigRecharge;
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
import java.util.List;

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
    @Autowired
    private TConfigRechargeMapper tConfigRechargeMapper;


    @Override
    public PreOrderResult createClientOrder(String tel, Float moneySum) throws Exception {
        Boolean result = EParkeClient.checkPhone(tel);
        if (!result) {
            throw new RuntimeException("不能给该手机号充值");
        }
        TRechargeDetail detail = createOrder(tel, moneySum);
        PreOrderResult preOrderResult = weChatPayService.getWechatPreOrderResult(detail.getUserId(), moneySum, detail.getSysOrder(), WX_PAY_NOTIFY_URL);
        return preOrderResult;
    }

    @Override
    public Boolean modifyNotifyPayed(String order, String outOrder) {
        Long id = rechargeDetailMapper.selectOrderIdByOrder(order);
        if (id == null) {
            logger.error("订单回调时发现我方单号不存在，返回值为：我方单号：" + order + " 对方单号：" + outOrder);
            return false;
        }
        TRechargeDetail detail = rechargeDetailMapper.selectByPrimaryKey(id);
        setOrderPayed(order, outOrder);
        Boolean result =  EParkeClient.topUp(detail.getTel(), detail.getTel(), new Date(), detail.getRechargeAmount());
        return result;
    }

    @Override
    public PagingResponse<List<TRechargeDetail>> getTRechargeList(RechargeFilter filter) {
        PagingResponse<List<TRechargeDetail>> response = new PagingResponse<>();
        List<TRechargeDetail> details = rechargeDetailMapper.selectTRechargeDetailByFilter(filter);
        Integer count = rechargeDetailMapper.countTRechargeDetailByFilter(filter);
        response.setTotalCount(count);
        response.setAaData(details);
        return response;
    }

    @Override
    public ServiceResponse addConfigRecharge(TConfigRecharge recharge) {
        User user = ApplicationContextUtils.getUser();
        ServiceResponse response = new ServiceResponse();
        Integer num = tConfigRechargeMapper.countTConfigRechargeByFilter(null);
        if (num > 5) {
            response.setCode("500001");
            response.setMessage("不能超过六条");
            return response;
        }
        if (recharge.getPrice() > recharge.getMoney()) {
            response.setCode("500002");
            response.setMessage("售价不得大于充值金额");
            return response;
        }
        recharge.setName(user.getName());
        tConfigRechargeMapper.insertSelective(recharge);
        return response;
    }

    @Override
    public void deleteConfigRecharge(Integer id) {
        tConfigRechargeMapper.deleteByPrimaryKey(id.longValue());
    }

    @Override
    public ServiceResponse updateConfigRecharge(TConfigRecharge recharge) {
        ServiceResponse response = new ServiceResponse();
        if (recharge.getPrice() > recharge.getMoney()) {
            response.setCode("500002");
            response.setMessage("售价不得大于充值金额");
            return response;
        }
        tConfigRechargeMapper.updateByPrimaryKeySelective(recharge);
        return response;
    }

    @Override
    public PagingResponse<List<TConfigRecharge>> selectConfigRechargeByFilter(ConfigRechargeFilter filter) {
        PagingResponse<List<TConfigRecharge>> response = new PagingResponse<>();
        List<TConfigRecharge> details = tConfigRechargeMapper.selectTConfigRechargeByFilter(filter);
        Integer count = tConfigRechargeMapper.countTConfigRechargeByFilter(filter);
        response.setTotalCount(count);
        response.setAaData(details);
        return response;
    }

    @Override
    public List<TConfigRecharge> getAllTConfigRecharge() {
        return tConfigRechargeMapper.selectByExample(null);
    }

    private void setOrderPayed(String orderCode, String outOrderCode) {
        Long orderId = rechargeDetailMapper.selectOrderIdByOrder(orderCode);
        if (orderId == null) {
            throw new RuntimeException("订单号:" + orderId + "不存在");
        }
        if (outOrderCode != null) {
            rechargeDetailMapper.updateOutPayCodeById(outOrderCode, orderId);
            rechargeDetailMapper.updatePayedById(orderId);
        }

    }

    private TRechargeDetail createOrder(String tel, Float moneySum) {
        User user = ApplicationContextUtils.getUser();
        Integer price = tConfigRechargeMapper.selectPriceByMoney(moneySum);
        if (moneySum <= 0) {
            throw new RuntimeException("不能充值负数");
        }
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        TRechargeDetail detail = new TRechargeDetail();
        detail.setUserName(user.getName());
        detail.setSysOrder(createOrderCode());
        detail.setRechargeAmount(moneySum);
        detail.setTel(tel);
        detail.setUserId(user.getId());
        detail.setPrice(price);
        rechargeDetailMapper.insertSelective(detail);
        return detail;
    }

    private String createOrderCode() {
        String pix = String.valueOf(System.nanoTime());
        return pix + RandomStringUtils.random(8, "1234567890");
    }

}
