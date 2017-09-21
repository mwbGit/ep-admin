package com.ep.controller.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.activity.api.ActivityTypeVO;
import com.ep.controller.common.ServiceResponse;
import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.model.activity.ActivityType;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.service.activity.api.IActivityService;

@RequestMapping(value = "/activity")
@Controller
public class ActivityController {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IActivityService activityService;


    @RequestMapping(value = "/type/list")
    @ResponseBody
    public Map<String, Object> typeList(Integer iDisplayStart, Integer iDisplayLength) {

        List<ActivityType> types = activityMapper.selectActivityTypeList(new PagingFilter(iDisplayStart, iDisplayLength));
        int count = activityMapper.countActivityTypeList(new PagingFilter(iDisplayStart, iDisplayLength));

        List<ActivityTypeVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(types)) {

            if (CollectionUtils.isNotEmpty(types)) {
                for (int i = 0; i < types.size(); i++) {
                    ActivityType type = types.get(i);
                    ActivityTypeVO vo = new ActivityTypeVO();

                    vo.setId(type.getId());
                    vo.setName(type.getName());
                    vo.setSequence(type.getSequence());
                    if (i == 0) {
                        vo.setFirst(true);
                    } else {
                        vo.setFirst(false);
                    }

                    if (i == types.size() - 1) {
                        vo.setEnd(true);
                    } else {
                        vo.setEnd(false);
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

    @RequestMapping(value = "/type/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestParam(value = "id") Integer id) {

        ActivityType activityType = new ActivityType();
        activityType.setId(id);
        activityType.setDeleted(Bool.Y);

        activityMapper.updateActivityType(activityType);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/type/modify")
    @ResponseBody
    public ServiceResponse modify(@RequestParam(value = "id") Integer id, String name, Boolean asc) {

        if (asc == null) {
            ActivityType activityType = new ActivityType();
            activityType.setId(id);
            activityType.setName(name);

            activityMapper.updateActivityType(activityType);
        } else {
            activityService.modifyActivityTypeSequence(id, asc);
        }

        return new ServiceResponse();
    }

    @RequestMapping(value = "/type/add")
    @ResponseBody
    public ServiceResponse modify(@RequestParam(value = "name") String name) {

        ActivityType activityType = new ActivityType();
        activityType.setName(name);
        activityType.setDeleted(Bool.N);

        activityMapper.insertActivityType(activityType);

        return new ServiceResponse();
    }

}
