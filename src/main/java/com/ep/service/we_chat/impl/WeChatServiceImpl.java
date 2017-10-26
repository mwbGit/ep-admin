package com.ep.service.we_chat.impl;

import com.alibaba.fastjson.JSON;
import com.ep.dao.model.common.PreOrderResult;
import com.ep.service.we_chat.WeChatService;
import com.ep.service.we_chat.pay.WeChatPayService;
import com.ep.service.we_chat.pay.api.AccessTokenAndOpenId;
import com.ep.service.we_chat.pay.api.WxPayConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fangchen.chai on 2017/10/22
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    private final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    @Autowired
    WxPayConfig wxpayConfig;


    @Override
    public AccessTokenAndOpenId getAccessTokenAndOpenId(String code) throws IOException {
        String urlStr = URL_ACCESS_TOKEN.replaceAll("APPID", wxpayConfig.getAppid()).replaceAll("SECRET", wxpayConfig.getAppsecret()).replace("JSCODE", code);

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        String lines;
        StringBuilder sb = new StringBuilder();
        //接受返回的数据
        while ((lines = reader.readLine()) != null) {
            sb.append(lines);
        }
        reader.close();

        // 断开连接
        connection.disconnect();

        AccessTokenAndOpenId result = JSON.parseObject(sb.toString(), AccessTokenAndOpenId.class);
        if (result == null || result.getErrcode() != null || result.getOpenid() == null) {
            throw new RuntimeException( "获取微信openId失败");
        }
        return result;
    }
}
