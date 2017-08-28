package com.ep.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.user.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface UserMapper {

    public User selectUser(@Param("name") String name, @Param("password") String password);

    public User selectUserById(@Param("id") Integer id);

    public List<User> selectUserList(@Param("start") Integer start, @Param("size") Integer size);

    public int countUserList();

    public void insertOrUpdateUser(User user);

    @Select("select id from t_user where mobile = #{tel}")
    Integer selectUserIdByTel(@Param("tel") String tel);

}
