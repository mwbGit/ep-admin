package com.ep.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.user.UserDao;

@Controller
public class TestController {

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/test")
    public String loginHtml() {

        String str= userDao.get("2");
        String str1= userDao.get("2");
        userDao.update("2");
        String str11= userDao.get("2");
        userDao.removeAll();
        String str111= userDao.get("2");


        return "index";
    }

}
