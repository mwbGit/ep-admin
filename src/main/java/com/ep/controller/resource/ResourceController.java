package com.ep.controller.resource;

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

import com.ep.controller.resource.api.ResourceResponse;
import com.ep.controller.resource.api.ResourceVO;
import com.ep.dao.filter.ActivityFilter;
import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.model.activity.Activity;
import com.ep.dao.model.activity.ActivityType;
import com.ep.dao.model.activity.Address;
import com.ep.dao.model.activity.AddressDetail;
import com.ep.dao.model.banner.BannerType;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.complain.DimensionType;
import com.ep.dao.model.complain.ServiceItemType;
import com.ep.dao.model.space.Space;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {
    @Autowired
    private SpaceMapper spaceMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @RequestMapping(value = "/activity/type/list")
    @ResponseBody
    public ResourceResponse activityTypeList() {
        List<ResourceVO> vos = new ArrayList<>();

        List<ActivityType> activityTypes = activityMapper.selectActivityTypeList(new PagingFilter());

        if (CollectionUtils.isNotEmpty(activityTypes)) {
            for (ActivityType type : activityTypes) {
                ResourceVO vo = new ResourceVO();
                vo.setLabel(type.getName());
                vo.setValue(type.getId());

                vos.add(vo);
            }
        }

        return new ResourceResponse(vos);
    }

    @RequestMapping(value = "/activity/list")
    @ResponseBody
    public ResourceResponse activityList() {
        List<ResourceVO> vos = new ArrayList<>();

        List<Activity> activities = activityMapper.selectActivityList(new ActivityFilter());

        if (CollectionUtils.isNotEmpty(activities)) {
            for (Activity activity : activities) {
                ResourceVO vo = new ResourceVO();
                vo.setLabel(activity.getTitle());
                vo.setValue(activity.getId());

                vos.add(vo);
            }
        }

        return new ResourceResponse(vos);
    }

    @RequestMapping(value = "/address/list")
    @ResponseBody
    public ResourceResponse addressList() {
        List<ResourceVO> vos = new ArrayList<>();

        List<Address> activityTypes = activityMapper.selectAllAddressList();

        if (CollectionUtils.isNotEmpty(activityTypes)) {
            for (Address address : activityTypes) {
                ResourceVO vo = new ResourceVO();
                vo.setLabel(address.getName());
                vo.setValue(address.getId());

                vos.add(vo);
            }
        }

        return new ResourceResponse(vos);
    }

    @RequestMapping(value = "/address/detail/list")
    @ResponseBody
    public ResourceResponse addressDetailList(@RequestParam Integer addressId) {
        List<ResourceVO> vos = new ArrayList<>();

        List<AddressDetail> activityTypes = activityMapper.selectAllAddressDetailListByAddressId(addressId);

        if (CollectionUtils.isNotEmpty(activityTypes)) {
            for (AddressDetail address : activityTypes) {
                ResourceVO vo = new ResourceVO();
                vo.setLabel(address.getName());
                vo.setValue(address.getId());

                vos.add(vo);
            }
        }

        return new ResourceResponse(vos);
    }

    @RequestMapping(value = "/space/list")
    @ResponseBody
    public ResourceResponse spaceList() {
        List<ResourceVO> vos = new ArrayList<>();
        List<Space> spaces = spaceMapper.selectSpaceList();

        if (CollectionUtils.isNotEmpty(spaces)) {
            for (Space space : spaces) {
                ResourceVO vo = new ResourceVO();
                vo.setLabel(space.getName());
                vo.setValue(space.getId());

                vos.add(vo);
            }
        }

        return new ResourceResponse(vos);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list() {
        List<ResourceVO> items = new ArrayList<>();
        for (ServiceItemType type : ServiceItemType.values()) {
            ResourceVO vo = new ResourceVO();
            vo.setLabel(type.getDescription());
            vo.setValue(type.getCode());

            items.add(vo);
        }

        List<ResourceVO> dimensions = new ArrayList<>();
        for (DimensionType type : DimensionType.values()) {
            ResourceVO vo = new ResourceVO();
            vo.setLabel(type.getDescription());
            vo.setValue(type.getCode());

            dimensions.add(vo);
        }
        List<ResourceVO> bannerTypes = new ArrayList<>();
        for (BannerType type : BannerType.values()) {
            ResourceVO vo = new ResourceVO();
            vo.setLabel(type.getDescription());
            vo.setValue(type.getCode());

            bannerTypes.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("items",items);
        map.put("dimensions",dimensions);
        map.put("bannerTypes",bannerTypes);
        return map;
    }

}
