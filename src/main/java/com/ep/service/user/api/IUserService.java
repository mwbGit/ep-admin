package com.ep.service.user.api;

import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface IUserService {
    public void modifyUser(Integer[] spaceIds, User user);

    public String createUser(String openId, String mobile, String name);

    public User getUserById(Integer id);

    public User getUserByToken(String token);

    public String getTokenByOpenCode(String openCode);

    public User getUserByPassword(String name, String password);

}
