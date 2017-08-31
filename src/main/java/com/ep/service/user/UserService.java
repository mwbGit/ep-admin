package com.ep.service.user;

import java.util.Arrays;
import java.util.List;

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
}
