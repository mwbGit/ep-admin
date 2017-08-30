package com.ep.service.user.api;

import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface IUserService {
    public void modifyUser(Integer[] spaceIds, User user);
}
