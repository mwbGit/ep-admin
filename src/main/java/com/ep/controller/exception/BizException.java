package com.ep.controller.exception;

/**
 * Created by MengWeiBo on 2017-03-31
 */
public class BizException extends RuntimeException {

	private String code;

	private String msg;

	public BizException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static final BizException PARAM_ERR = new BizException("10001", "参数错误");

}
