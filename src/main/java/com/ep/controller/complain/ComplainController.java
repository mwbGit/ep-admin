package com.ep.controller.complain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.api.ServiceResponse;
import com.ep.controller.complain.api.ComplainVO;
import com.ep.controller.complain.api.DimensionRequest;
import com.ep.controller.complain.api.DimensionVO;
import com.ep.dao.mapper.ComplainMapper;
import com.ep.dao.model.complain.*;

@Controller
@RequestMapping(value = "/complain")
public class ComplainController {

    @Resource
    private ComplainMapper complainMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> complainList(Integer typeId, Integer iDisplayStart, Integer iDisplayLength) {
        List<Complain> complains = complainMapper.selectComplain(typeId, iDisplayStart, iDisplayLength);

        int count = complainMapper.countComplain(typeId);

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", ComplainVO.toVOs(complains));
        map.put("recordsTotal", count);
//        map.put("draw", 1);
        map.put("recordsFiltered", count);

        return map;
    }


    @RequestMapping(value = "/dimension/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam("itemId") Integer itemId) {
        List<Dimension> dimensions = complainMapper.selectDimension(itemId);

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", DimensionVO.toVOs(dimensions));

//        map.put("recordsTotal", 1);
//        map.put("draw", 1);
//        map.put("recordsFiltered", 1);
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
    public ServiceResponse delete(@RequestParam("id") Integer id) {

        complainMapper.deleteDimensionById(id);

        return new ServiceResponse();
    }


    @RequestMapping(value = "/item/list")
    @ResponseBody
    public Map<String, Object> itemList() {
        Map<String, Object> map = new HashMap<>();
        List<ServiceItem> items = complainMapper.selectItem();
        map.put("data", items);
        map.put("empty", CollectionUtils.isEmpty(items));

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
    public ServiceResponse itemDelete(@RequestParam("id") Integer id) {

        complainMapper.deleteDimensionByItemId(id);
        complainMapper.deleteServiceItem(id);

        return new ServiceResponse();
    }
}
