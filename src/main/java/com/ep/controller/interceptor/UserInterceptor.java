package com.ep.controller.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.user.User;

/**
 * Created by fangchen.chai on 2017/4/5.
 */
public class UserInterceptor implements HandlerInterceptor {
    public String[] allowUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

//        String requestUrl = request.getRequestURI().replace(
//                request.getContextPath(), "");
//
//        if (null != allowUrls && allowUrls.length >= 1)
//            for (String url : allowUrls) {
//                if (requestUrl.contains(url)) {
//                    return true;
//                }
//            }
//
//        User user = (User) request.getSession().getAttribute("user");
//
//
//        if (user != null) {
//            return true;
//        } else {
//            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
//
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public String[] getAllowUrls() {
        return allowUrls;
    }

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }
}
