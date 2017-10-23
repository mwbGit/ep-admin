package com.ep.controller.wx;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.wx.user.UserBindRequest;
import com.ep.controller.wx.user.UserBindResponse;
import com.ep.service.user.api.IUserService;
import com.ep.service.we_chat.WeChatService;
import com.ep.service.we_chat.pay.api.AccessTokenAndOpenId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/wx")
@Controller
public class WxUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private WeChatService weChatService;


    @RequestMapping(value = "/bind")
    @ResponseBody
    public UserBindResponse bind(@RequestBody UserBindRequest request) {
        UserBindResponse response = new UserBindResponse();
        if (StringUtils.isBlank(request.getMobile()) ||
                StringUtils.isBlank(request.getMobile())){
            response.setCode("-1");
            response.setMessage("手机号或者OpenId为空");
            return response;
        }

        String token = userService.createUser(request.getOpenId(), request.getMobile());

        response.setToken(token);
        return response;
    }

    @RequestMapping(value = "/openid")
    @ResponseBody
    public AccessTokenAndOpenId getOpenId(@RequestParam("code") String code) throws IOException {

        return weChatService.getAccessTokenAndOpenId(code);

    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ServiceResponse test() {
        System.out.println(1);
        return new ServiceResponse();
    }

    @RequestMapping(value = "/test1")
    @ResponseBody
    public ServiceResponse test1() {
        System.out.println(1);

        return new ServiceResponse();
    }

}
