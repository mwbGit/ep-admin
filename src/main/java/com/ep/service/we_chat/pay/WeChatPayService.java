package com.ep.service.we_chat.pay;


import com.ep.dao.model.common.PreOrderResult;

/**
 * Created by fangchen.chai on 2017/8/2
 */
public interface WeChatPayService {
    /**
     * 生成微信支付相关的参数
     *
     * @param clientId
     * @param code
     * @param moneySum
     * @param wxPayNotifyUrl 微信的回调地址
     * @return
     */
    public PreOrderResult getWechatPreOrderResult(Integer clientId, Float moneySum, String code, String wxPayNotifyUrl);
}
