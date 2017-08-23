package com.ep.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.user.UserDao;

@Controller
public class TestController {

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/index.html")
    public String loginHtml() {

        return "index";
    }

}
