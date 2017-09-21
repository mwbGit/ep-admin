package com.ep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.user.UserDao;
import com.ep.service.user.UserService;
import com.ep.service.user.api.IUserService;

@Controller
public class TestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/test")
    public String test(Integer id) throws Exception {

        if (id != null && id ==1){
            throw new Exception("aaaaaaaa");
        }

        userService.test();
        return "index";
    }

}
