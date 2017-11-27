package com.ep.controller.epark.api;

import com.ep.controller.common.ServiceResponse;

/**
 * @auther fangchen.chai ON 2017/11/27
 */
public class BalanceResponse extends ServiceResponse{

    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
