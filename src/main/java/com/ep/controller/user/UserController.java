package com.ep.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.user.api.UserVO;
import com.ep.dao.mapper.UserMapper;
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

}
