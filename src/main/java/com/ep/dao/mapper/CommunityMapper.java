package com.ep.dao.mapper;

import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface CommunityMapper {

    public List<Device> selectDeviceList(@Param("filter") PagingFilter filter);

    public int countDeviceList(@Param("filter") PagingFilter filter);

    public int selectMaxDeviceSequence();

    public Device selectDeviceById(@Param("id") Integer id);

    public int countDeviceByName(@Param("name") String name, @Param("id") Integer id);

    public Device selectDeviceBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

    public void updateDevice(Device device);

    public void insertDevice(Device device);

    public Community selectCommunityById(@Param("id") Integer id);

    public List<String> selectCommunityPictureByCommunityId(@Param("communityId") Integer communityId);

    public List<Integer> selectCommunityDeviceByCommunityId(@Param("communityId") Integer communityId);

    public List<Integer> selectCommunityUserByCommunityId(@Param("communityId") Integer communityId);

    public List<Community> selectCommunityList(@Param("filter") PagingFilter filter);

    public int countCommunityList(@Param("filter") PagingFilter filter);

    public void updateCommunity(Community community);

    public void insertCommunity(Community community);

    public void batchInsertCommunityUser(@Param("userIds") List<Integer> userIds, @Param("communityId") Integer communityId);

    public void batchInsertCommunityDevice(@Param("deviceIds") List<Integer> deviceIds, @Param("communityId") Integer communityId);

    public void batchInsertCommunityPicture(@Param("pictures") List<String> pictures, @Param("communityId") Integer communityId);

    public void batchDeleteCommunityDevice( @Param("communityId") Integer communityId);

    public void batchDeleteCommunityUser( @Param("communityId") Integer communityId);

    public void batchDeleteCommunityPicture( @Param("communityId") Integer communityId);

}
