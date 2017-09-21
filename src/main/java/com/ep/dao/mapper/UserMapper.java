package com.ep.dao.mapper;

import java.util.List;

import com.ep.dao.filter.UserFilter;
import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.user.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface UserMapper {

    public User selectUserByPassword(@Param("name") String name, @Param("password") String password);

    public User selectUserByToken(@Param("token") String token);

    public User selectUserById(@Param("id") Integer id);

    public User selectUserByMobile(@Param("openId") String openId, @Param("mobile") String mobile);

    public List<User> selectUserList(@Param("filter") UserFilter filter);

    public int countUserList(@Param("filter") UserFilter filter);

    public void insertOrUpdateUser(User user);

    public void insertOrUpdateUserToken(@Param("userId") Integer userId, @Param("token") String token);


}
