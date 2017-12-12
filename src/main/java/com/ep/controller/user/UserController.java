package com.ep.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.user.api.UserVO;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.filter.UserFilter;
import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.mapper.UserMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.space.UserSpace;
import com.ep.dao.model.user.User;
import com.ep.service.user.api.IUserService;
import com.ep.util.DateTimeUtility;
import com.ep.util.MD5;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpaceMapper spaceMapper;

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse<List<UserVO>> list(Integer iDisplayStart, Integer iDisplayLength, String sSearch, Boolean managed) {
        UserFilter filter = new UserFilter();
        if (iDisplayStart != null && iDisplayLength != null) {
            filter.setStart(iDisplayStart);
            filter.setSize(iDisplayLength);
        }
        filter.setName(sSearch);
        filter.setManaged(Bool.fromValue(managed));

        List<User> users = userMapper.selectUserList(filter);
        int count = userMapper.countUserList(filter);

        List<UserVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(users)) {

            if (CollectionUtils.isNotEmpty(users)) {
                for (User user : users) {
                    UserVO vo = new UserVO();
                    vo.setId(user.getId());
                    vo.setName(user.getName());
                    vo.setCompany(user.getCompany());
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

        PagingResponse<List<UserVO>> response = new PagingResponse<>();
        response.setTotalCount(count);
        response.setAaData(vos);

        return response;
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

            userMapper.updateUser(user);
        }

        return new ServiceResponse();
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public ServiceResponse modify(Integer[] spaceIds, @RequestParam(value = "id") Integer id) {

        User curUser = ApplicationContextUtils.getUser();
        User user = new User();
        user.setId(id);
        user.setUpdateDate(new Date());
        user.setUpdatedById(curUser.getId());
        user.setUpdatedByName(curUser.getName());

        userService.modifyUser(spaceIds, user);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/modify/managed")
    @ResponseBody
    public ServiceResponse modifyManaged(@RequestParam(value = "id") Integer id,
                                         @RequestParam(value = "managed") Boolean managed) {

        User curUser = ApplicationContextUtils.getUser();

        User user = new User();
        user.setId(id);
        user.setManaged(Bool.fromValue(managed));
        user.setUpdateDate(new Date());
        user.setUpdatedById(curUser.getId());
        user.setUpdatedByName(curUser.getName());

        userMapper.updateUser(user);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/reset/password")
    @ResponseBody
    public ServiceResponse modifyPassword(@RequestParam(value = "password") String password) {

        User user = ApplicationContextUtils.getUser();
        user.setPassword(MD5.md5(password));

        userMapper.updateUser(user);

        return new ServiceResponse();
    }
}
