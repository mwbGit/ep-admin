package com.ep.controller.wx.community;

import com.ep.controller.common.PagingRequest;
import com.ep.controller.common.PagingResponse;
import com.ep.controller.community.api.CommunityDeviceVO;
import com.ep.controller.user.api.UserVO;
import com.ep.controller.wx.community.api.CommunityInfoResponse;
import com.ep.controller.wx.community.api.CommunityVO;
import com.ep.dao.filter.CommunityFilter;
import com.ep.dao.filter.CommunitySpaceFilter;
import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.community.ActivityMeetingSpace;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
import com.ep.dao.model.user.User;
import com.ep.service.community.api.ICommunityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * -
 * Created by mengweibo on 2017/12/28.
 */

@RequestMapping(value = "/wx/community")
@Controller
public class WxCommunityController {

    @Autowired
    private ICommunityService communityService;

    @Autowired
    private CommunityMapper communityMapper;

    @RequestMapping(value = "/detail")
    @ResponseBody
    public CommunityInfoResponse detail(@RequestParam(value = "id") Integer id) {
        CommunityInfoResponse response = new CommunityInfoResponse();

        Community community = communityService.getCommunity(id);

        if (community != null) {
            response.setId(id);
            response.setName(community.getName());
            response.setTag(community.getTag());
            response.setTips(community.getTips());
            response.setContent(community.getContent());
            response.setPictures(community.getPictures());

            List<UserVO> userVOs = new ArrayList<>();
            List<CommunityDeviceVO> deviceVOs = new ArrayList<>();
            response.setUsers(userVOs);
            response.setDevices(deviceVOs);

            if (CollectionUtils.isNotEmpty(community.getUsers())) {
                for (User user : community.getUsers()) {
                    UserVO vo = new UserVO();
                    vo.setName(user.getName());
                    vo.setImg(user.getImg());
                    vo.setMobile(user.getMobile());
                    userVOs.add(vo);
                }
            }

            if (CollectionUtils.isNotEmpty(community.getDevices())) {
                for (Device device : community.getDevices()) {
                    CommunityDeviceVO vo = new CommunityDeviceVO();
                    vo.setName(device.getName());
                    vo.setImg(device.getImg());
                    deviceVOs.add(vo);
                }
            }
        }

        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse list(PagingRequest request) {
        PagingResponse<List<CommunityVO>> response = new PagingResponse<>();
        CommunityFilter filter = new CommunityFilter();
        filter.setSize(request.getPageSize());
        filter.setStart((request.getPageNumber() - 1) * request.getPageSize());
        filter.setOnline(Bool.Y);

        List<Community> communities = communityService.getCommunityList(filter);
        response.setData(CommunityVO.toVOs(communities));

        return response;
    }

    @RequestMapping(value = "/space/list")
    @ResponseBody
    public PagingResponse spaceList(PagingRequest request, Boolean activity) {
        CommunitySpaceFilter filter = new CommunitySpaceFilter();
        filter.setSize(request.getPageSize());
        filter.setStart((request.getPageNumber() - 1) * request.getPageSize());
        filter.setOnline(Boolean.TRUE);
        filter.setType(1);
        if (activity != null && activity) {
            filter.setType(0);
        }
        List<ActivityMeetingSpace> spaces = communityMapper.selectActivityMeetingSpaceList(filter);
        PagingResponse<List<ActivityMeetingSpace>> response = new PagingResponse<>();
        response.setData(spaces);

        return response;
    }
}
