package com.ep.controller.visitor;

import com.alibaba.fastjson.JSONObject;
import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.visitor.api.CustomVisitorBean;
import com.ep.controller.visitor.api.VisitorService;
import com.ep.controller.visitor.api.RoomVisitorBean;
import com.ep.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 吴晓海 on 2018/1/4.
 */
@RequestMapping(value = "/visitor")
@Controller
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    @RequestMapping(value = "/roomList")
    @ResponseBody
    public PagingResponse<List<RoomVisitorBean>> roomList(Integer iDisplayStart, Integer iDisplayLength, String sSearch) {
        PagingResponse<List<RoomVisitorBean>> response = new PagingResponse<>();
        List<Map<String, Object>> list = visitorService.getRoomVisitorList(iDisplayStart,iDisplayLength,sSearch);
        List<RoomVisitorBean> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map map : list) {
                RoomVisitorBean rv = new RoomVisitorBean();
                rv.setId((int)map.get("id"));
                rv.setCall((int)map.get("call"));
                rv.setlDate(DateTimeUtility.formatYYYYMMDD((Date) map.get("l_date")));
                rv.setlTime(DateTimeUtility.formatHHMM((Date)map.get("l_time")));
                rv.setName((String) map.get("name"));
                rv.setOrderTime(DateTimeUtility.formatYYYYMMDDHHMM((Date)map.get("order_time")));
                rv.setRemark((String) map.get("remark"));
                vos.add(rv);
            }
        }
        int count = visitorService.getRoomVisitorListCount(sSearch);
        response.setTotalCount(count);
        response.setAaData(vos);
        return response;
    }

    @RequestMapping(value = "/customList")
    @ResponseBody
    public PagingResponse<List<CustomVisitorBean>> customList(Integer iDisplayStart, Integer iDisplayLength, String sSearch) {
        PagingResponse<List<CustomVisitorBean>> response = new PagingResponse<>();
        List<Map<String, Object>> list = visitorService.getCustomVisitorList(iDisplayStart,iDisplayLength,sSearch);
        List<CustomVisitorBean> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map map : list) {
                CustomVisitorBean rv = new CustomVisitorBean();
                rv.setId((int)map.get("id"));
                rv.setlName((String) map.get("l_name"));
                rv.setsName((String) map.get("s_name"));
                rv.setsCompanyName((String) map.get("s_company_name"));
                rv.setCarNo((String) map.get("car_no"));
                rv.setlDate(DateTimeUtility.formatYYYYMMDD((Date) map.get("l_date")));
                rv.setlTime(DateTimeUtility.formatHHMM((Date)map.get("l_time")));
                vos.add(rv);
            }
        }
        int count = visitorService.getCustomVisitorListCount(sSearch);
        response.setTotalCount(count);
        response.setAaData(vos);
        return response;
    }

    @RequestMapping(value = "/addCustom")
    @ResponseBody
    public ServiceResponse addCustom(HttpServletRequest request) {
        ServiceResponse responsereReturn = new ServiceResponse();
        String date = request.getParameter("date");
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSONObject.parseObject(date);
        visitorService.addCustomVisitor(jsonObject);
        return responsereReturn;
    }

    @RequestMapping(value = "/addRoom")
    @ResponseBody
    public ServiceResponse addRoom(HttpServletRequest request) {
        ServiceResponse responsereReturn = new ServiceResponse();
        String date = request.getParameter("date");
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSONObject.parseObject(date);
        visitorService.addRoomVisitor(jsonObject);
        return responsereReturn;
    }
}
