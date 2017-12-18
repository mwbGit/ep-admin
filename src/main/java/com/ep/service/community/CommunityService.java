package com.ep.service.community;

import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.community.CommunityDevice;
import com.ep.service.community.api.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class CommunityService implements ICommunityService {

//    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public void modifyCommunityDeviceSequence(Integer id, Boolean asc) {

        CommunityDevice communityDevice = communityMapper.selectCommunityDeviceById(id);

        CommunityDevice replaceDevice = communityMapper.selectCommunityDeviceBySequence(communityDevice.getSequence(), asc);

        int sequence = communityDevice.getSequence();
        communityDevice.setSequence(replaceDevice.getSequence());
        replaceDevice.setSequence(sequence);

        communityMapper.updateCommunityDevice(communityDevice);

        communityMapper.updateCommunityDevice(replaceDevice);
    }
}
