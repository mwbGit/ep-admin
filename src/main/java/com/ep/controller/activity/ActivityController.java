package com.ep.controller.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ep.controller.activity.api.ActivityTypeVO;
import com.ep.controller.activity.api.ActivityVO;
import com.ep.controller.activity.api.AddActivityRequest;
import com.ep.controller.activity.api.AddActivityResponse;
import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.dao.filter.ActivityFilter;
import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.model.activity.Activity;
import com.ep.dao.model.activity.ActivityType;
import com.ep.dao.model.activity.AddressDetail;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.service.activity.api.IActivityService;
import com.ep.service.upload.api.IUploadService;
import com.ep.util.DateTimeUtility;

@RequestMapping(value = "/activity")
@Controller
public class ActivityController {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IUploadService uploadService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse<List<ActivityVO>> list(Integer iDisplayStart, Integer iDisplayLength, String sSearch, Integer typeId) {
        PagingResponse<List<ActivityVO>> response = new PagingResponse<>();

        ActivityFilter filter = new ActivityFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        filter.setTitle(sSearch);
        filter.setTypeId(typeId);

        List<Activity> activities = activityMapper.selectActivityList(filter);
        int count = activityMapper.countActivityList(filter);

        response.setTotalCount(count);
        response.setAaData(ActivityVO.toVOs(activities));

        return response;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ServiceResponse add(@RequestParam("imgUpload") MultipartFile uploadFile, AddActivityRequest activityRequest) throws Exception {
        ServiceResponse response = new ServiceResponse();

        String url = uploadService.uploadImage(uploadFile);

        if (url == null && activityRequest.getId() == null){
            response.setCode("1");
            response.setMessage("上传失败");
            return response;
        } else if (activityRequest.getId() != null){
            Activity activity = activityMapper.selectActivityById(activityRequest.getId());
            url = activity.getImg();
        }

        Activity activity = new Activity();
        activity.setId(activityRequest.getId());
        activity.setTitle(activityRequest.getTitle());
        activity.setStartTime(DateTimeUtility.parseYYYYMMDDHHMM(activityRequest.getStartTime()));
        activity.setEndTime(DateTimeUtility.parseYYYYMMDDHHMM(activityRequest.getEndTime()));
        activity.setPrice(activityRequest.getPrice());
        activity.setContent(activityRequest.getContent());
        activity.setImg(url);
        activity.setOnline(Bool.N);
        ActivityType type = new ActivityType();
        type.setId(activityRequest.getTypeId());
        activity.setType(type);
        AddressDetail detail = new AddressDetail();
        detail.setId(activityRequest.getAddressId());
        activity.setAddressDetail(detail);

        activityMapper.insertOrUpdateActivity(activity);

        return response;
    }

    @RequestMapping(value = "/publish")
    @ResponseBody
    public ServiceResponse publish(@RequestParam("id") Integer id) throws Exception {
        ServiceResponse response = new ServiceResponse();

        activityMapper.updateActivityOnline(id);

        return response;
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public AddActivityResponse detail(@RequestParam("id") Integer id) throws Exception {
        AddActivityResponse response = new AddActivityResponse();

        Activity activity = activityMapper.selectActivityById(id);
        if (activity != null) {
            response.setId(activity.getId());
            response.setImg(activity.getImg());
            response.setTitle(activity.getTitle());
            response.setPrice(activity.getPrice());
            response.setContent(activity.getContent());
            response.setStartTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getStartTime()));
            response.setEndTime(DateTimeUtility.formatYYYYMMDDHHMM(activity.getEndTime()));
            response.setTypeId(activity.getType().getId());
            response.setAddressId(activity.getAddressDetail().getAddress().getId());
            response.setAddressDetailId(activity.getAddressDetail().getId());
        }

        return response;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestParam("id") Integer id) throws Exception {
        ServiceResponse response = new ServiceResponse();

        activityMapper.deleteActivity(id);

        return response;
    }

    @RequestMapping(value = "/type/list")
    @ResponseBody
    public PagingResponse<List<ActivityTypeVO>> typeList(Integer iDisplayStart, Integer iDisplayLength) {

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
                    vo.setSequence(iDisplayStart + i + 1);
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

        PagingResponse<List<ActivityTypeVO>> response = new PagingResponse<>();
        response.setTotalCount(count);
        response.setAaData(vos);

        return response;
    }

    @RequestMapping(value = "/type/delete")
    @ResponseBody
    public ServiceResponse typeDelete(@RequestParam(value = "id") Integer id) {

        ActivityType activityType = new ActivityType();
        activityType.setId(id);
        activityType.setDeleted(Bool.Y);

        activityMapper.updateActivityType(activityType);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/type/modify")
    @ResponseBody
    public ServiceResponse typpModify(@RequestParam(value = "id") Integer id, String name, Boolean asc) {
        ServiceResponse response = new ServiceResponse();

        if (asc == null) {
            int count = activityMapper.countActivityByName(name, id);
            if (count > 0) {
                response.setCode("1");
                response.setMessage("名称重复");
                return response;
            }

            ActivityType activityType = new ActivityType();
            activityType.setId(id);
            activityType.setName(name);

            activityMapper.updateActivityType(activityType);
        } else {

            activityService.modifyActivityTypeSequence(id, asc);
        }

        return response;
    }

    @RequestMapping(value = "/type/add")
    @ResponseBody
    public ServiceResponse typeAdd(@RequestParam(value = "name") String name) {
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isBlank(name)) {
            response.setCode("1");
            response.setMessage("名称为空");
            return response;
        }
        int count = activityMapper.countActivityByName(name, null);
        if (count > 0) {
            response.setCode("2");
            response.setMessage("名称重复");
            return response;
        }

        int sequence = activityMapper.selectMaxActivitySequence();

        ActivityType activityType = new ActivityType();
        activityType.setName(name);
        activityType.setDeleted(Bool.N);
        activityType.setSequence(sequence);

        activityMapper.insertActivityType(activityType);

        return response;
    }

}
