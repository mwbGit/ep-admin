package com.ep.dao.mapper;

import com.ep.dao.filter.CommunitySpaceFilter;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.community.ActivityMeetingSpace;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface CommunityMapper {

    List<Device> selectDeviceList(@Param("filter") PagingFilter filter);

    int countDeviceList(@Param("filter") PagingFilter filter);

    int selectMaxDeviceSequence();

    Device selectDeviceById(@Param("id") Integer id);

    int countDeviceByName(@Param("name") String name, @Param("id") Integer id);

    Device selectDeviceBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

    void updateDevice(Device device);

    void insertDevice(Device device);

    Community selectCommunityById(@Param("id") Integer id);

    List<String> selectCommunityPictureByCommunityId(@Param("communityId") Integer communityId);

    List<Integer> selectCommunityDeviceByCommunityId(@Param("communityId") Integer communityId);

    List<Integer> selectCommunityUserByCommunityId(@Param("communityId") Integer communityId);

    List<Community> selectCommunityList(@Param("filter") PagingFilter filter);

    int countCommunityList(@Param("filter") PagingFilter filter);

    void updateCommunity(Community community);

    void insertCommunity(Community community);

    void batchInsertCommunityUser(@Param("userIds") List<Integer> userIds, @Param("communityId") Integer communityId);

    void batchInsertCommunityDevice(@Param("deviceIds") List<Integer> deviceIds, @Param("communityId") Integer communityId);

    void batchInsertCommunityPicture(@Param("pictures") List<String> pictures, @Param("communityId") Integer communityId);

    void batchDeleteCommunityDevice(@Param("communityId") Integer communityId);

    void batchDeleteCommunityUser(@Param("communityId") Integer communityId);

    void batchDeleteCommunityPicture(@Param("communityId") Integer communityId);

    List<ActivityMeetingSpace> selectActivityMeetingSpaceList(@Param("filter") CommunitySpaceFilter filter);

    int countActivityMeetingSpaceList(@Param("filter") CommunitySpaceFilter filter);

    void deleteActivityMeetingSpaceDevice(@Param("spaceId") Integer spaceId);

    void batchDeleteActivityMeetingSpaceDevice(@Param("spaceId") Integer spaceId);

    void batchInsertActivityMeetingSpaceDevice(@Param("deviceIds") List<Integer> deviceIds,
                                               @Param("spaceId") Integer spaceId);

    void insertActivityMeetingSpace(ActivityMeetingSpace space);

    void updateActivityMeetingSpace(ActivityMeetingSpace space);

    ActivityMeetingSpace selectActivityMeetingSpaceById(@Param("id") Integer id);

    List<Integer> selectActivityMeetingSpaceDevice(@Param("spaceId") Integer spaceId);

}
