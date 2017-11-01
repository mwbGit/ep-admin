package com.ep.service.adviceService;

import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class AdviceService  {




    public void adviceService(Integer[] spaceIds, User user) {


        if (spaceIds != null && spaceIds.length > 0) {
            List<Integer> list = Arrays.asList(spaceIds);

        }
    }
}
