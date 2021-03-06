package com.ep.controller.common;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-03-31
 */
public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final ServiceResponse DEFAULT = new ServiceResponse();
    public static final ServiceResponse ERROR = new ServiceResponse("500", "系统错误");

    private String code = "0";

    private String message = "成功";

    public ServiceResponse() {
    }

    public ServiceResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
