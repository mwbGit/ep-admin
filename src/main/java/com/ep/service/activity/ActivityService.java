package com.ep.service.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.model.activity.ActivityType;
import com.ep.service.activity.api.IActivityService;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void modifyActivityTypeSequence(Integer id, Boolean asc) {

        ActivityType activityType = activityMapper.selectActivityTypeById(id);

        ActivityType replaceType = activityMapper.selectActivityBySequence(activityType.getSequence(), asc);

        int sequence = activityType.getSequence();
        activityType.setSequence(replaceType.getSequence());
        replaceType.setSequence(sequence);

        activityMapper.updateActivityType(activityType);

        activityMapper.updateActivityType(replaceType);
    }
}
