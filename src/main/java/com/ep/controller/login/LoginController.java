package com.ep.controller.login;

import javax.servlet.http.HttpServletRequest;

import com.ep.service.user.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.user.User;
import com.ep.util.MD5;


@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ServiceResponse login(HttpServletRequest request) {
        ServiceResponse response = new ServiceResponse();
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        pwd = MD5.md5(pwd);

        User user = userService.getUserByPassword(username, pwd);

        if (user == null) {
            response.setCode("1");
            response.setMessage("用户名或密码错误");
        }else {
            ApplicationContextUtils.setUser(user);
        }

        return response;
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        ApplicationContextUtils.removeUser();
        return "login";
    }
}
