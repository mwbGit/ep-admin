package com.ep.service.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;
import com.ep.service.we_chat.WeChatService;
import com.ep.service.we_chat.pay.api.AccessTokenAndOpenId;
import com.ep.util.MD5;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpaceMapper spaceMapper;

    @Autowired
    private WeChatService weChatService;

    @Override
    public void modifyUser(Integer[] spaceIds, User user) {
        userMapper.insertOrUpdateUser(user);

        spaceMapper.deleteUserSpaceByUserId(user.getId());

        if (spaceIds != null && spaceIds.length > 0) {
            List<Integer> list = Arrays.asList(spaceIds);
            spaceMapper.batchInsertUserSpace(list, user.getId());
        }
    }

    @Override
    public String createUser(String openCode, String mobile, String name) {
        String openId = "";
        try {
            AccessTokenAndOpenId andOpenId = weChatService.getAccessTokenAndOpenId(openCode);
            if (andOpenId != null) {
                openId = andOpenId.getOpenid();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setMobile(mobile);
        user.setOpenId(openId);
        user.setName(name);
        user.setDeleted(Bool.N);

        userMapper.insertOrUpdateUser(user);

        String token = MD5.md5(openCode + mobile + new Date().getTime());

        userMapper.insertOrUpdateUserToken(user.getId(), openCode, token);

        ApplicationContextUtils.setUser(user);

        return token;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.selectUserByToken(token);
    }

    @Override
    public String getTokenByOpenCode(String openCode) {
        return userMapper.selectTokenByOpenCode(openCode);
    }

    @Override
    public User getUserByPassword(String name, String password) {
        return userMapper.selectUserByPassword(name, password);
    }
}
