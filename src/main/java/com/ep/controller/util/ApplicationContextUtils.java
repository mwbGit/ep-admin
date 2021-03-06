package com.ep.controller.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ep.dao.model.user.User;

public class ApplicationContextUtils {


    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static String getDomain() {
        String url = getRequest().getScheme() + "://" + getRequest().getServerName();
        if (getRequest().getServerName().equals("localhost")) {
            url += ":" + getRequest().getServerPort();
        }
        url += getRequest().getContextPath();

        return url;
    }

    public static User getUser() {
        return (User) getSession().getAttribute("user");
    }

    public static void setUser(User user) {
        getSession().setAttribute("user", user);
    }

    public static void removeUser() {
        getSession().removeAttribute("user");
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }


}
