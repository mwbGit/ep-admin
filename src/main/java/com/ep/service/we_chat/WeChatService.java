package com.ep.service.we_chat;

import com.ep.service.we_chat.pay.api.AccessTokenAndOpenId;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by fangchen.chai on 2017/10/22
 */
public interface WeChatService {
    /**
     * 获取openid
     *
     * @param code 微信网页跳转返回的code参数
     * @return
     */
    AccessTokenAndOpenId getAccessTokenAndOpenId(String code) throws IOException;

}
