package com.ep.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.api.ServiceResponse;
import com.ep.controller.user.api.UserVO;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.user.User;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Integer iDisplayStart, Integer iDisplayLength) {

        List<User> users = userMapper.selectUserList(iDisplayStart, iDisplayLength);
        int count = userMapper.countUserList();

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", UserVO.toVOs(users));
        map.put("iTotalDisplayRecords", count);
        map.put("iTotalRecords", count);

        return map;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestParam(value = "id") Integer id) {

        User user = userMapper.selectUserById(id);

        if (user != null) {
            if (user.getDeleted() == Bool.Y) {
                user.setDeleted(Bool.N);
            } else {
                user.setDeleted(Bool.Y);
            }

            User curUser = ApplicationContextUtils.getUser();
            user.setUpdateDate(new Date());
            user.setUpdatedById(curUser.getId());
            user.setUpdatedByName(curUser.getName());

            userMapper.insertOrUpdateUser(user);
        }

        return new ServiceResponse();
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public ServiceResponse modify(List<Integer> spaceIds, @RequestParam(value = "id") Integer id) {

        User user = userMapper.selectUserById(id);


        return new ServiceResponse();
    }
}
