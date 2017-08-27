package com.ep.service.recharge;

import com.ep.dao.model.common.PreOrderResult;

/**
 * Created by fangchen.chai on 2017/8/27
 */
public interface RechargeService {
    PreOrderResult clientOrder(String  tel, Float moneySum);

    public Boolean notifyPayed(String code, String outCode);
}
