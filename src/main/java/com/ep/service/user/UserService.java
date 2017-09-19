package com.ep.service.user;

import java.util.Arrays;
import java.util.List;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.common.Bool;
import com.ep.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpaceMapper spaceMapper;

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
    public String createUser(String openId, String mobile) {
        User user = userMapper.selectUserByMobile(openId, mobile);
        if (user == null) {
            user = new User();
            user.setMobile(mobile);
            user.setOpenId(openId);
            user.setName(mobile);
            user.setDeleted(Bool.N);
            userMapper.insertOrUpdateUser(user);
        }

        String token = null;
        if (!user.getDeleted().getValue()) {
            token = MD5.md5(openId + mobile);
            userMapper.insertOrUpdateUserToken(user.getId(), token);

            ApplicationContextUtils.setUser(user);
        }
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
    public User getUserByPassword(String name, String password) {
        return userMapper.selectUserByPassword(name, password);
    }
}
