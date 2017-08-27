package com.ep.controller.user;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.user.api.UserVO;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.space.UserSpace;
import com.ep.dao.model.user.User;
import com.ep.util.DateTimeUtility;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpaceMapper spaceMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Integer iDisplayStart, Integer iDisplayLength) {

        List<User> users = userMapper.selectUserList(iDisplayStart, iDisplayLength);
        int count = userMapper.countUserList();

        List<UserVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(users)) {

            if (CollectionUtils.isNotEmpty(users)) {
                for (User user : users) {
                    UserVO vo = new UserVO();
                    vo.setId(user.getId());
                    vo.setName(user.getName());
                    vo.setImg(user.getImg());
                    vo.setSex(user.getSex());
                    vo.setMobile(user.getMobile());
                    vo.setRemark(user.getRemark());
                    vo.setCreateDate(DateTimeUtility.formatYYYYMMDD(user.getCreateDate()));
                    vo.setUpdateDate(DateTimeUtility.formatYYYYMMDD(user.getUpdateDate()));
                    vo.setUpdatedByName(user.getUpdatedByName());
                    vo.setDeleted(user.getDeleted().getValue());


                    List<UserSpace> userSpaces = spaceMapper.selectUserSpaceByUserId(user.getId());
                    if (CollectionUtils.isNotEmpty(userSpaces)) {
                        StringBuilder sb = new StringBuilder();
                        for (UserSpace userSpace : userSpaces) {
                            sb.append(userSpace.getSpace().getName());
                            sb.append(" ");
                        }
                        vo.setSpaceNames(sb.toString());
                    }

                    vos.add(vo);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", vos);
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

        User curUser = ApplicationContextUtils.getUser();
        User user = userMapper.selectUserById(id);
        user.setUpdateDate(new Date());
        user.setUpdatedById(curUser.getId());
        user.setUpdatedByName(curUser.getName());

        userMapper.insertOrUpdateUser(user);

        spaceMapper.deleteUserSpaceByUserId(id);

        spaceMapper.batchInsertUserSpace(spaceIds, id);

        return new ServiceResponse();
    }
}
