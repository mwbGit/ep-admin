package com.ep.controller.order.api;

import java.math.BigDecimal;

/**
 * Created by fangchen.chai on 2017/8/20.
 */
public class CreateOrderRequest {


    private String tel;

    private BigDecimal pay;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }
}
