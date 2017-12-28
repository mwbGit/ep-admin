package com.ep.service.community;

import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.filter.CommunityFilter;
import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.community.ActivityMeetingSpace;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
import com.ep.dao.model.user.User;
import com.ep.service.community.api.ICommunityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class CommunityService implements ICommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public void modifyDeviceSequence(Integer id, Boolean asc) {

        Device device = communityMapper.selectDeviceById(id);

        Device replaceDevice = communityMapper.selectDeviceBySequence(device.getSequence(), asc);

        int sequence = device.getSequence();
        device.setSequence(replaceDevice.getSequence());
        replaceDevice.setSequence(sequence);

        communityMapper.updateDevice(device);

        communityMapper.updateDevice(replaceDevice);
    }

    @Override
    public void addCommunity(Community community, List<Integer> userIds, List<Integer> deviceIds) {

        if (community.getId() == null) {
            communityMapper.insertCommunity(community);
        } else {
            communityMapper.updateCommunity(community);
        }

        communityMapper.batchDeleteCommunityDevice(community.getId());

        communityMapper.batchDeleteCommunityUser(community.getId());

        if (CollectionUtils.isNotEmpty(userIds)) {
            communityMapper.batchInsertCommunityUser(userIds, community.getId());
        }
        if (CollectionUtils.isNotEmpty(deviceIds)) {
            communityMapper.batchInsertCommunityDevice(deviceIds, community.getId());
        }
        if (CollectionUtils.isNotEmpty(community.getPictures())) {
            communityMapper.batchInsertCommunityPicture(community.getPictures(), community.getId());
        }
    }

    @Override
    public Community getCommunity(Integer id) {
        Community community = communityMapper.selectCommunityById(id);
        setCommunity(community);

        return community;
    }

    public void setCommunity(Community community){
        if (community != null) {
            Integer id = community.getId();
            List<Device> devices = communityMapper.selectCommunityDeviceByCommunityId(id);
            List<String> urls = communityMapper.selectCommunityPictureByCommunityId(id);
            List<User> users = communityMapper.selectCommunityUserByCommunityId(id);

            community.setDevices(devices);
            community.setUsers(users);
            community.setPictures(urls);
        }
    }

    @Override
    public List<Community> getCommunityList(CommunityFilter  filter) {
        List<Community> communitys = communityMapper.selectCommunityList(filter);

        if (CollectionUtils.isNotEmpty(communitys)) {
            for (Community community: communitys) {
                setCommunity(community);
            }

        }
        return communitys;
    }

    @Override
    public void addActivityMeetingSpace(ActivityMeetingSpace space) {
        User user = ApplicationContextUtils.getUser();

        //todo
        if (user != null) {
            space.setUpdatedById(user.getId());
            space.setUpdatedByName(user.getName());
        }
        communityMapper.insertActivityMeetingSpace(space);

        if (CollectionUtils.isNotEmpty(space.getDevices())) {
            communityMapper.batchInsertActivityMeetingSpaceDevice(space.getDevices(), space.getId());
        }

        Community community = communityMapper.selectCommunityById(space.getCommunityId());
        if (space.getType() == 0) {
            community.setActivityNum(community.getActivityNum() + 1);
        } else {
            community.setMeetingNum(community.getMeetingNum() + 1);
        }

        communityMapper.updateCommunity(community);
    }

    @Override
    public void updateActivityMeetingSpace(ActivityMeetingSpace space) {
        User user = ApplicationContextUtils.getUser();

        //todo
        if (user != null) {
            space.setUpdatedById(user.getId());
            space.setUpdatedByName(user.getName());
        }
        communityMapper.updateActivityMeetingSpace(space);

        communityMapper.batchDeleteActivityMeetingSpaceDevice(space.getId());

        if (CollectionUtils.isNotEmpty(space.getDevices())) {
            communityMapper.batchInsertActivityMeetingSpaceDevice(space.getDevices(), space.getId());
        }
    }

    @Override
    public void deleteActivityMeetingSpace(Integer spaceId) {
        ActivityMeetingSpace space = communityMapper.selectActivityMeetingSpaceById(spaceId);
        Community community = communityMapper.selectCommunityById(space.getCommunityId());

        if (space.getType() == 0) {
            community.setActivityNum(community.getActivityNum() - 1);
        } else {
            community.setMeetingNum(community.getMeetingNum() - 1);
        }

        communityMapper.updateCommunity(community);

        communityMapper.batchDeleteActivityMeetingSpaceDevice(spaceId);

        communityMapper.deleteActivityMeetingSpaceDevice(spaceId);
    }
}
