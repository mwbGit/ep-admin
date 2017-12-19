package com.ep.service.community.api;

import com.ep.dao.model.community.Community;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface ICommunityService {

    public void modifyDeviceSequence(Integer id, Boolean asc);

    public void addCommunity(Community community);

    public Community getCommunity(Integer id);

}
