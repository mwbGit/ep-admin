package com.ep.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.model.complain.DimensionType;
import com.ep.dao.model.complain.ServiceItemType;
import com.ep.dao.model.space.Space;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {
    @Autowired
    private SpaceMapper spaceMapper;

    @RequestMapping(value = "/space/list")
    @ResponseBody
    public Map<String, Object> spaceList() {
        Map<String, Object> result = new HashMap<>();
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

        result.put("data", vos);

        return result;
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

        Map<String, Object> map = new HashMap<>();
        map.put("items",items);
        map.put("dimensions",dimensions);
        return map;
    }

}
