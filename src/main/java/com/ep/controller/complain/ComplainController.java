package com.ep.controller.complain;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.api.ServiceResponse;
import com.ep.controller.complain.api.DimensionRequest;
import com.ep.dao.mapper.ComplainMapper;
import com.ep.dao.model.complain.Dimension;
import com.ep.dao.model.complain.DimensionType;
import com.ep.dao.model.complain.ServiceItem;
import com.ep.dao.model.complain.ServiceItemType;

@Controller
@RequestMapping(value = "/complain")
public class ComplainController {

    @Resource
    private ComplainMapper complainMapper;

    @RequestMapping(value = "/dimension/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("aaData", complainMapper.selectDimension());
        map.put("recordsTotal", 1);
        map.put("draw", 1);
        map.put("recordsFiltered", 1);
        return map;
    }

    @RequestMapping(value = "/dimension/add")
    @ResponseBody
    public ServiceResponse add(DimensionRequest request) {
        Dimension dimension = new Dimension();
        dimension.setName(request.getName());
        dimension.setRatio(request.getRatio());
        dimension.setType(DimensionType.fromCode(request.getType()));
        dimension.setItemId(request.getItemId());

        complainMapper.insertDimension(dimension);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/dimension/modify")
    @ResponseBody
    public ServiceResponse modify(DimensionRequest request) {
        Dimension dimension = new Dimension();
        dimension.setId(request.getId());
        dimension.setName(request.getName());
        dimension.setRatio(request.getRatio());
        dimension.setType(DimensionType.fromCode(request.getType()));

        complainMapper.updateDimension(dimension);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/dimension/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestBody Map<String, Integer> map) {

        complainMapper.deleteDimension(map.get("id"));

        return new ServiceResponse();
    }


    @RequestMapping(value = "/item/list")
    @ResponseBody
    public Map<String, Object> itemList() {
        Map<String, Object> map = new HashMap<>();
        map.put("aaData", complainMapper.selectItem());
        map.put("recordsTotal", 1);
        map.put("draw", 1);
        map.put("recordsFiltered", 1);
        return map;
    }

    @RequestMapping(value = "/item/add")
    @ResponseBody
    public ServiceResponse itemAdd(ItemRequest request) {
        ServiceItem item = new ServiceItem();
        item.setName(request.getName());
        item.setRatio(request.getRatio());
        item.setType(ServiceItemType.fromCode(request.getType()));

        complainMapper.insertServiceItem(item);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/item/modify")
    @ResponseBody
    public ServiceResponse itemModify(ItemRequest request) {
        ServiceItem item = new ServiceItem();
        item.setId(request.getId());
        item.setName(request.getName());
        item.setRatio(request.getRatio());
        item.setType(ServiceItemType.fromCode(request.getType()));

        complainMapper.updateServiceItem(item);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/item/delete")
    @ResponseBody
    public ServiceResponse itemDelete(@RequestBody Map<String, Integer> map) {

        complainMapper.deleteServiceItem(map.get("id"));

        return new ServiceResponse();
    }
}
