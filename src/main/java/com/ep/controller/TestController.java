package com.ep.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.user.UserDao;

@Controller
public class TestController {

//    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/test")
    public String test(Integer id) throws Exception {

        if (id != null && id ==1){
            throw new Exception("aaaaaaaa");
        }


        return "index";
    }

}
