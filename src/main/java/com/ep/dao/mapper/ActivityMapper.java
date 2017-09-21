package com.ep.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.activity.ActivityType;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface ActivityMapper {

    public List<ActivityType> selectActivityTypeList(@Param("filter")PagingFilter filter);

    public int countActivityTypeList(@Param("filter")PagingFilter filter);

    public ActivityType selectActivityById(@Param("id") Integer id);

    public ActivityType selectActivityBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

    public void updateActivityType(ActivityType activityType);

    public void insertActivityType(ActivityType activityType);


}
