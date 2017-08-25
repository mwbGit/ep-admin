package com.ep.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.user.User;


@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ServiceResponse login(HttpServletRequest request) {
        ServiceResponse response = new ServiceResponse();
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        User user = userMapper.selectUser(username, pwd);

        if (user == null) {
            response.setCode("1");
            response.setMessage("用户名或密码错误");
        }else {
            ApplicationContextUtils.getSession().setAttribute("user", user);
        }

        return response;
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        ApplicationContextUtils.getSession().removeAttribute("user");
        return "login";
    }
}
