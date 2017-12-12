package com.ep.dao.mapper;

import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.communit.CommunityDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface CommunityMapper {

    public List<CommunityDevice> selectCommunityDeviceList(@Param("filter") PagingFilter filter);

    public int countCommunityDeviceList(@Param("filter") PagingFilter filter);

    public int selectMaxCommunityDeviceSequence();

    public CommunityDevice selectCommunityDeviceById(@Param("id") Integer id);

    public int countCommunityDeviceByName(@Param("name") String name, @Param("id") Integer id);

    public CommunityDevice selectCommunityDeviceBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

    public void updateCommunityDevice(CommunityDevice communityDevice);

    public void insertCommunityDevice(CommunityDevice communityDevice);


}
