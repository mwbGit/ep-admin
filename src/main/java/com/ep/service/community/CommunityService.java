package com.ep.service.community;

import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
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
    public void addCommunity(Community community) {

        if (community.getId() == null) {
            communityMapper.insertCommunity(community);
        } else {
            communityMapper.updateCommunity(community);
        }

        communityMapper.batchInsertCommunityUser(community.getUserIds(), community.getId());

        communityMapper.batchInsertCommunityDevice(community.getDevices(), community.getId());

        if (CollectionUtils.isNotEmpty(community.getPictures())) {
            communityMapper.batchInsertCommunityPicture(community.getPictures(), community.getId());
        }
    }

    @Override
    public Community getCommunity(Integer id) {
        Community community = communityMapper.selectCommunityById(id);

        if (community != null) {
            List<Integer> devices = communityMapper.selectCommunityDeviceByCommunityId(id);
            List<String> urls = communityMapper.selectCommunityPictureByCommunityId(id);
            List<Integer> users = communityMapper.selectCommunityUserByCommunityId(id);

            community.setDevices(devices);
            community.setUserIds(users);
            community.setPictures(urls);
        }

        return community;
    }
}
