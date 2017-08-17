package com.ep.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.user.UserDao;

@Controller
public class TestController {

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/login")
    public String loginHtml() {

        String name = userDao.get("2");
        System.out.println(name);

        userDao.update("2");
        String name1 = userDao.get("2");
        System.out.println(name1);

        userDao.removeAll();

        String name2 = userDao.get("2");
        System.out.println(name2);

        System.out.println("2111");
        return "login";
    }

}
