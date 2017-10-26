package com.ep.controller.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WxUserInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (1 == 1) {
            return true;
        }
        User user = ApplicationContextUtils.getUser();
        if (user != null) {
            return true;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", -1);
        jsonObject.put("message", "未绑定手机号");
        String method = request.getMethod();
        String token = null;

        if (method.equalsIgnoreCase("GET")) {
            token = (String) request.getAttribute("token");
        } else {
            token = getTokenParma(request);
        }
        if (!StringUtils.isBlank(token)) {
            user = userService.getUserByToken(token);
            if (user != null) {
                ApplicationContextUtils.setUser(user);
                return true;
            }
            jsonObject.put("message", "登录过期");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String getTokenParma(HttpServletRequest request) {
        StringBuilder data = new StringBuilder();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();

            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JSONObject object = JSONObject.parseObject(data.toString());
        if (object != null) {
            return (String) object.get("token");
        }
        return null;
    }

}
