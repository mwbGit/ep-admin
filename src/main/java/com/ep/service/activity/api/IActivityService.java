package com.ep.service.activity.api;

import com.ep.dao.model.activity.Activity;

/**
 * Created by MengWeiBo on 2017-08-07
 */
public interface IActivityService {

    public void modifyActivityTypeSequence(Integer id, Boolean asc);

    public boolean addActivityUser(Activity activity, Integer userId);

}
