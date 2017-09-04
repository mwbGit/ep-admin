package com.ep.service.epclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ep.dao.model.common.Bool;
import com.ep.http.SimpleHttpClient;
import com.ep.service.we_chat.pay.util.HttpKit;
import com.ep.util.DateTimeUtility;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by fangchen.chai on 2017/9/3
 */
public class EParkeClient {
    private static final String host = "http://ecard.epark.com:8080/EparkPort";
    private static final String checkPhoneUrl = host+"/validationUser";
    private static final String TopUpUrl = host+"/topUp";


    public static Boolean topUp(String operationPhone, String topUpPhone, Date time, Double money) {
        //money 是元，这里转成分
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("type", 1);
        jsonParam.put("operationPhone", operationPhone);
        jsonParam.put("topUpPhone", topUpPhone);
        jsonParam.put("time", DateTimeUtility.formatYYYYMMDDHHMMSS(time));
        jsonParam.put("money", money*100);
        try {
            String json = jsonPost(TopUpUrl, jsonParam);
            Map<String,String> obj = (Map<String, String>) JSON.parse(json);
            if (obj.get("state").equals("200")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("充值出错");
        }
    }

    public static Boolean checkPhone(String phone){
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("phone", phone);
        try {
            String json = jsonPost(checkPhoneUrl, jsonParam);
            Map<String,String> obj = (Map<String, String>) JSON.parse(json);
            if (obj.get("state").equals("200")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("连接eParke出错");
        }
    }

    private static String jsonPost(String url, JSONObject jsonParam) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

//        json方式

        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        System.out.println();
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }



    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("phone","18511332532");
//        map.put("startTime","2014-01-01 00:00:00");
//        map.put("endTime","2018-01-01 00:00:00");
//        map.put("signature","678980");
//        String sta = JSONObject.toJSONString(map);
//        String star = HttpKit.post("http://ecard.epark.com:8080/EparkPort/validationUser",sta);
//        SimpleHttpClient simpleHttpClient = new SimpleHttpClient(10, 10, 10);
//        String str = simpleHttpClient.post("http://ecard.epark.com:8080/EparkPort/getRechargeRecord", map, null);
//        System.out.println(star);
//        String url = "http://ecard.epark.com:8080/EparkPort/validationUser";
//        HttpPost httpPost = new HttpPost(url);
//        CloseableHttpClient client = HttpClients.createDefault();
//        String respContent = null;
//
//        json方式
//        JSONObject jsonParam = new JSONObject();
//        jsonParam.put("phone","18511332532");
//        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        System.out.println();
//        HttpResponse resp = client.execute(httpPost);
//        if(resp.getStatusLine().getStatusCode() == 200) {
//            HttpEntity he = resp.getEntity();
//            respContent = EntityUtils.toString(he,"UTF-8");
//        }
//        System.out.println(respContent);
        Boolean b = topUp("18511332532", "18511332532", new Date(), 100d);

        System.out.println(b);
    }
}
