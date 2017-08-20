package com.ep.service.we_chat.pay.api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxPayConfig {

	@Value("$(wxpay.openid)")
	public String openId;
	/**
	 * 公众账号ID
	 * appId等都是平台的
	 */
	@Value("${wxpay.appid}")
	private String appid;
	@Value("${wxpay.appsecret}")
	private String appsecret;
	/**
	 * 商户号
	 */
	@Value("${wxpay.mchid}")
	private String mchid;
/*
	*//**
	 * 服务商appid
	 *//*
	@Value("${wxpay.subAppid}")
	private String subAppid;

	*//**
	 * 服务商商户号
	 *//*
	@Value("${wxpay.subMchid}")
	private String subMchid;*/

	/**
	 * 签名
	 */
	@Value("${wxpay.sign}")
	private String sign;

	/**
	 * 证书
	 */
	@Value("${wxpay.cert}")
	private String cert;

	/**
	 * 通知地址
	 */
	@Value("${wxpay.notifyUrl}")
	private String notifyUrl;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}
/*
	public String getSubAppid() {
		return subAppid;
	}

	public void setSubAppid(String subAppid) {
		this.subAppid = subAppid;
	}

	public String getSubMchid() {
		return subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}*/

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}
