package com.ep.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.space.Space;
import com.ep.dao.model.space.UserSpace;
import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface SpaceMapper {

    public List<Space> selectSpaceList();

    public List<UserSpace> selectUserSpaceByUserId(@Param("userId") Integer userId);

    public void deleteUserSpaceByUserId(@Param("userId") Integer userId);

    public void batchInsertUserSpace(@Param("spaces") List<Integer> spaces, @Param("userId") Integer userId);

}
