package com.ep.service.community.api;

import com.ep.dao.filter.CommunityFilter;
import com.ep.dao.model.community.ActivityMeetingSpace;
import com.ep.dao.model.community.Community;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface ICommunityService {

    public void modifyDeviceSequence(Integer id, Boolean asc);

    public void addCommunity(Community community, List<Integer> userIds, List<Integer> deviceIds);

    public void addActivityMeetingSpace(ActivityMeetingSpace space);

    public void updateActivityMeetingSpace(ActivityMeetingSpace space);

    public void deleteActivityMeetingSpace(Integer spaceId);

    public Community getCommunity(Integer id);

    public List<Community> getCommunityList(CommunityFilter filter);

}
