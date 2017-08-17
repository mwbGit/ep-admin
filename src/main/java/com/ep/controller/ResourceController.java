package com.ep.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.api.ResourceVO;
import com.ep.dao.model.complain.DimensionType;
import com.ep.dao.model.complain.ServiceItemType;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

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
