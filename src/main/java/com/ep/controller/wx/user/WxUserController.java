package com.ep.controller.wx.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.ep.controller.exception.BizException;
import com.ep.dao.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.controller.wx.user.api.UserBindRequest;
import com.ep.controller.wx.user.api.UserInfoResponse;
import com.ep.controller.wx.user.api.UserTokenResponse;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;
import com.ep.service.we_chat.WeChatService;
import com.ep.service.we_chat.pay.api.AccessTokenAndOpenId;

@RequestMapping(value = "/wx/user")
@Controller
public class WxUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatService weChatService;

    @RequestMapping(value = "/bind")
    @ResponseBody
    public UserTokenResponse bind(@RequestBody UserBindRequest request, HttpServletResponse httpServletResponse) {
        UserTokenResponse response = new UserTokenResponse();
        if (StringUtils.isBlank(request.getOpenCode()) ||
                StringUtils.isBlank(request.getMobile())) {
            response.setCode("1");
            response.setMessage("手机号或者OpenCode为空");
            return response;
        }

        String token = userService.createUser(request.getOpenCode(), request.getMobile(), request.getName());
        if (token == null) {
            response.setCode("2");
            response.setMessage("绑定失败");

        } else {
            Cookie tokenCookie = new Cookie("token", token);
            Cookie openCodeCookie = new Cookie("openCode", request.getOpenCode());

            httpServletResponse.addCookie(openCodeCookie);
            httpServletResponse.addCookie(tokenCookie);
        }

        response.setToken(token);
        return response;
    }

    @RequestMapping(value = "/openid")
    @ResponseBody
    public AccessTokenAndOpenId getOpenId(@RequestParam("code") String code) throws IOException {

        return weChatService.getAccessTokenAndOpenId(code);

    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public UserInfoResponse info() {
        UserInfoResponse response = new UserInfoResponse();
        User user = ApplicationContextUtils.getUser();
        user = userService.getUserById(user.getId());

        if (user != null) {
            response.setName(user.getName());
            response.setCompany(user.getCompany());
            response.setMobile(user.getMobile());
            response.setOpenId(user.getOpenId());
            response.setRemark(user.getRemark());
            response.setSex(user.getSex());
            response.setDeleted(user.getDeleted().getValue());
        }

        return response;
    }

    @RequestMapping(value = "/token")
    @ResponseBody
    public UserTokenResponse getToken(@RequestParam("openCode") String openCode) {
        UserTokenResponse response = new UserTokenResponse();

        String openId = userService.getOpenId(openCode);
        if (openId != null) {
            String token = userMapper.selectTokenByOpenId(openId);

            if (token != null) {
                response.setToken(token);
            } else {
                throw BizException.BIND_MOBILE;
            }
        } else {
            throw BizException.PARAM_ERR;
        }

        return response;
    }

}
