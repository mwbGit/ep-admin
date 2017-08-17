package com.ep.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.dao.mapper.DimensionMapper;
import com.ep.dao.model.complain.Dimension;

@Controller
@RequestMapping(value = "/dimension")
public class DimensionController {

    @Resource
    private DimensionMapper dimensionDao;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("aaData",dimensionDao.selectDimension());
        map.put("recordsTotal",1);
        map.put("draw",1);
        map.put("recordsFiltered",1);
        return map;
    }

}
