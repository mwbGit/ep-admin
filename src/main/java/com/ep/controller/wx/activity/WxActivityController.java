package com.ep.controller.wx.activity;

import java.util.Date;
import java.util.List;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.model.user.User;
import com.ep.service.activity.ActivityService;
import com.ep.service.activity.api.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.PagingRequest;
import com.ep.controller.wx.activity.api.ActivityInfoResponse;
import com.ep.controller.wx.activity.api.ActivityListResponse;
import com.ep.controller.wx.activity.api.ActivityVO;
import com.ep.controller.wx.user.api.UserTokenResponse;
import com.ep.dao.filter.ActivityFilter;
import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.model.activity.Activity;
import com.ep.dao.model.common.Bool;
import com.ep.util.DateTimeUtility;

@RequestMapping(value = "/wx/activity")
@Controller
public class WxActivityController {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ActivityListResponse list(@RequestBody PagingRequest request) {
        ActivityListResponse response = new ActivityListResponse();
        ActivityFilter filter = new ActivityFilter();
//        filter.setUnFinished(true);
        filter.setOnline(Bool.Y);
        filter.setSize(request.getPageSize());
        filter.setStart((request.getPageNumber() - 1) * request.getPageSize());

        List<Activity> list = activityMapper.selectActivityList(filter);
        response.setData(ActivityVO.toVOs(list));
        return response;
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public ActivityInfoResponse detail(@RequestParam Integer id) {
        ActivityInfoResponse response = new ActivityInfoResponse();

        Activity activity = activityMapper.selectActivityById(id);
        if (activity != null) {
            response.setId(activity.getId());
            response.setImg(activity.getImg());
            response.setTitle(activity.getTitle());
            response.setContent(activity.getContent());
            response.setPrice(activity.getPrice());

            if (activity.getLimit() == null) {
                response.setLimit("无限制");
            } else {
                response.setLimit(String.valueOf(activity.getLimit()));
            }
            response.setStartTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getStartTime()));
            response.setEndTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getEndTime()));
            response.setTypeName(activity.getType().getName());
            response.setAddress(activity.getAddressDetail().getAddress().getName() + activity.getAddressDetail().getName());
        }

        return response;
    }

    @RequestMapping(value = "/enroll")
    @ResponseBody
    public ServiceResponse enroll(@RequestParam Integer id) {
        ServiceResponse response = new ServiceResponse();
        User user = ApplicationContextUtils.getUser();
        if (user == null) {
            response.setCode("1");
            response.setMessage("未登录");

            return response;
        }

        Activity activity = activityMapper.selectActivityById(id);
        Date now = new Date();

        if (!activity.getOnline().getValue()
                || now.after(activity.getStartTime())) {
            response.setCode("2");
            response.setMessage("报名已截止");

            return response;
        }

        boolean successful = activityService.addActivityUser(activity, user.getId());
        if (!successful) {
            response.setCode("3");
            response.setMessage("报名人数达到上限");
        }

        return response;
    }

}
