package com.ep.service.user.api;

import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface IUserService {
    public void modifyUser(Integer[] spaceIds, User user);

    public String getOpenId(String openCode);

    public String createUser(String openId, String mobile, String name);

    public User getUserById(Integer id);

    public User getUserByToken(String token);

    public String getTokenByOpenId(String openId);

    public User getUserByPassword(String name, String password);

}
