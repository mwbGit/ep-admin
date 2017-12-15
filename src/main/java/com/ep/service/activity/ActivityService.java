package com.ep.service.activity;

import com.ep.dao.filter.ActivityUserFilter;
import com.ep.dao.model.activity.Activity;
import com.ep.dao.model.activity.ActivityUser;
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

    @Override
    public synchronized boolean enrollActivity(Activity activity, Integer userId) {
        ActivityUserFilter filter = new ActivityUserFilter();
        filter.setActivityId(activity.getId());

        int count = activityMapper.countActivityUserList(filter);

        if (activity.getLimit() != null && activity.getLimit() <= count){
            return false;
        }

        ActivityUser activityUser = new ActivityUser();
        activityUser.setPrice(activity.getPrice());
        activityUser.setActivityId(activity.getId());
        activityUser.setUserId(userId);

        activityMapper.insertActivityUser(activityUser);

        return true;
    }
}
