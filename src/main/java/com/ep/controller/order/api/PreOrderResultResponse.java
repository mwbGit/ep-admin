package com.ep.controller.order.api;

import com.ep.controller.common.ServiceResponse;
import com.ep.dao.model.common.PreOrderResult;

/**
 * Created by fangchen.chai on 2017/8/27
 */
public class PreOrderResultResponse extends ServiceResponse {
    private PreOrderResult result;

    public PreOrderResult getResult() {
        return result;
    }

    public void setResult(PreOrderResult result) {
        this.result = result;
    }
}
