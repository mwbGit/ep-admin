package com.ep.service.recharge;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.dao.filter.ConfigRechargeFilter;
import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.model.common.PreOrderResult;
import com.ep.dao.model.generated.TConfigRecharge;
import com.ep.dao.model.generated.TRechargeDetail;

import java.util.List;

/**
 * Created by fangchen.chai on 2017/8/27
 */
public interface RechargeService {
    PreOrderResult createClientOrder(String  tel, Float moneySum) throws Exception;

    public Boolean notifyPayed(String code, String outCode);

    PagingResponse<List<TRechargeDetail>> getTRechargeList(RechargeFilter filter);

    ServiceResponse addConfigRecharge(TConfigRecharge recharge);

    void deleteConfigRecharge(Integer id);

    void updateConfigRecharge(TConfigRecharge recharge);

    PagingResponse<List<TConfigRecharge>> selectConfigRechargeByFilter(ConfigRechargeFilter filter);
}
