package com.ep.dao.mapper;

import java.util.List;

import com.ep.dao.filter.ActivityUserFilter;
import com.ep.dao.model.activity.*;
import org.apache.ibatis.annotations.Param;

import com.ep.dao.filter.ActivityFilter;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface ActivityMapper {

    public Activity selectActivityById(@Param("id") Integer id);

    public List<Activity> selectActivityList(@Param("filter") ActivityFilter filter);

    public int countActivityList(@Param("filter") ActivityFilter filter);

    public List<ActivityUser> selectActivityUserList(@Param("filter") ActivityUserFilter filter);

    public int countActivityUserList(@Param("filter") ActivityUserFilter filter);

    public void insertActivityUser(ActivityUser activityUser);

    public List<Address>  selectAllAddressList();

    public List<AddressDetail>  selectAllAddressDetailListByAddressId(@Param("addressId") Integer addressId);

    public void insertActivity(Activity activity);

    public void updateActivity(Activity activity);

    public void updateActivityOnline(@Param("id") Integer id);

    public void deleteActivity(@Param("id") Integer id);

    public List<ActivityType> selectActivityTypeList(@Param("filter") PagingFilter filter);

    public int countActivityTypeList(@Param("filter") PagingFilter filter);

    public ActivityType selectActivityTypeById(@Param("id") Integer id);

    public int countActivityByName(@Param("name") String name, @Param("id") Integer id);

    public int selectMaxActivitySequence();

    public ActivityType selectActivityBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

    public void updateActivityType(ActivityType activityType);

    public void insertActivityType(ActivityType activityType);


}
