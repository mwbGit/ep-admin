package com.ep.controller.space;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ep.dao.mapper.UserMapper;
import com.ep.dao.user.UserDao;

@Controller
@RequestMapping(value = "/space")
public class SpaceController {

    @Autowired
    private UserMapper userDao;

    @RequestMapping(value = "/index.html")
    public String loginHtml() {

        return "index";
    }

}
