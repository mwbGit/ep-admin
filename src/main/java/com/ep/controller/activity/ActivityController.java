package com.ep.controller.activity;

import com.ep.controller.activity.api.*;
import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.dao.filter.ActivityFilter;
import com.ep.dao.filter.ActivityUserFilter;
import com.ep.dao.mapper.ActivityMapper;
import com.ep.dao.mapper.SpaceMapper;
import com.ep.dao.model.activity.Activity;
import com.ep.dao.model.activity.ActivityType;
import com.ep.dao.model.activity.ActivityUser;
import com.ep.dao.model.activity.AddressDetail;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.space.UserSpace;
import com.ep.service.activity.api.IActivityService;
import com.ep.service.upload.api.IUploadService;
import com.ep.util.DateTimeUtility;
import com.ep.util.DownloadUtil;
import com.ep.util.POIUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/activity")
@Controller
public class ActivityController {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private SpaceMapper spaceMapper;

    @RequestMapping(value = "/user/list")
    @ResponseBody
    public PagingResponse<List<ActivityUserVO>> userList(
            Integer iDisplayStart, Integer iDisplayLength, Integer activityId, String sSearch) {
        PagingResponse<List<ActivityUserVO>> response = new PagingResponse<>();

        ActivityUserFilter filter = new ActivityUserFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        filter.setActivityId(activityId);
        filter.setUserName(sSearch);

        List<ActivityUser> activityUsers = activityMapper.selectActivityUserList(filter);
        int count = activityMapper.countActivityUserList(filter);

        response.setTotalCount(count);

        List<ActivityUserVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(activityUsers)) {
            int order = 1;
            for (ActivityUser activityUser : activityUsers) {
                ActivityUserVO vo = new ActivityUserVO();
                vo.setId(activityUser.getId());
                vo.setOrderId(order++);
                vo.setPrice(activityUser.getPrice());
                vo.setCreateDate(DateTimeUtility.formatYYYYMMDD(activityUser.getCreateDate()));
                vo.setName(activityUser.getUser().getName());
                vo.setCompany(activityUser.getUser().getCompany());
                vo.setSex(activityUser.getUser().getSex());
                vo.setMobile(activityUser.getUser().getMobile());

                List<UserSpace> userSpaces = spaceMapper.selectUserSpaceByUserId(activityUser.getUser().getId());
                if (CollectionUtils.isNotEmpty(userSpaces)) {
                    StringBuilder sb = new StringBuilder();
                    for (UserSpace userSpace : userSpaces) {
                        sb.append(userSpace.getSpace().getName());
                        sb.append(" ");
                    }
                    vo.setUserSpaces(sb.toString());
                }

                vos.add(vo);
            }
        }

        response.setAaData(vos);

        return response;
    }

    @RequestMapping("/user/list/excel")
    @ResponseBody
    public void queryComplainForExcel(HttpServletRequest request, HttpServletResponse response, Integer activityId) {
        ActivityUserFilter filter = new ActivityUserFilter();
        filter.setActivityId(activityId);

        List<ActivityUser> activityUsers = activityMapper.selectActivityUserList(filter);

        // 数据写入Excel
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("报名列表");
        // 通用样式
        XSSFCellStyle style = POIUtil.crateCellStyle(wb);
        // 第一行
        XSSFRow firstRow = sheet.createRow(0);
        XSSFCell c0 = firstRow.createCell(0);
        c0.setCellValue("序号");
        c0.setCellStyle(style);
        XSSFCell c1 = firstRow.createCell(1);
        c1.setCellValue("所属园区");
        c1.setCellStyle(style);
        XSSFCell c2 = firstRow.createCell(2);
        c2.setCellValue("公司名称");
        c2.setCellStyle(style);
        XSSFCell c3 = firstRow.createCell(3);
        c3.setCellValue("用户名");
        c3.setCellStyle(style);
        XSSFCell c4 = firstRow.createCell(4);
        c4.setCellValue("性别");
        c4.setCellStyle(style);
        XSSFCell c5 = firstRow.createCell(5);
        c5.setCellValue("手机号");
        c5.setCellStyle(style);
        XSSFCell c6 = firstRow.createCell(6);
        c6.setCellValue("报名时间");
        c6.setCellStyle(style);
        XSSFCell c7 = firstRow.createCell(7);
        c7.setCellValue("报名费用");
        c7.setCellStyle(style);

        // 写入数据
        XSSFRow row = null;
        XSSFCell cell = null;
        BigDecimal sum = BigDecimal.ZERO;
        String userSpacesStr = "";
        for (int i = 0; activityUsers != null && i < activityUsers.size(); i++) {
            ActivityUser tr = activityUsers.get(i);
            if (tr.getPrice() != null) {
                sum = sum.add(tr.getPrice());
            }

            List<UserSpace> userSpaces = spaceMapper.selectUserSpaceByUserId(tr.getUser().getId());
            if (CollectionUtils.isNotEmpty(userSpaces)) {
                StringBuilder sb = new StringBuilder();
                for (UserSpace userSpace : userSpaces) {
                    sb.append(userSpace.getSpace().getName());
                    sb.append(" ");
                }
                userSpacesStr = sb.toString();
            }

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(userSpacesStr);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(tr.getUser().getCompany());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(tr.getUser().getName());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(tr.getUser().getSex());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(tr.getUser().getMobile());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(DateTimeUtility.formatYYYYMMDD(tr.getCreateDate()));
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(tr.getPrice() + "");
            cell.setCellStyle(style);
        }

        if (activityUsers != null && activityUsers.size() > 0) {
            row = sheet.createRow(activityUsers.size() + 1);
            cell = row.createCell(6);
            cell.setCellValue(sum + "总计：");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(sum + "元");
            cell.setCellStyle(style);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 生成流对象
        try {
            wb.write(byteArrayOutputStream);// 将excel写入流
            // 工具类，封装弹出下载框：
            String outFile = "报名列表.xlsx";
            DownloadUtil down = new DownloadUtil();
            down.download(byteArrayOutputStream, request, response, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        if (url == null && activityRequest.getId() == null) {
            response.setCode("1");
            response.setMessage("上传失败");
            return response;
        } else if (url == null && activityRequest.getId() != null) {
            Activity activity = activityMapper.selectActivityById(activityRequest.getId());
            url = activity.getImg();
        }

        Activity activity = new Activity();
        activity.setId(activityRequest.getId());
        activity.setTitle(activityRequest.getTitle());
        activity.setLimit(activityRequest.getLimit());
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

        if (activity.getId() != null) {
            activityMapper.updateActivity(activity);
        } else {
            activityMapper.insertActivity(activity);
        }
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
            response.setLimit(activity.getLimit());
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
    public ServiceResponse typeModify(@RequestParam(value = "id") Integer id, String name, Boolean asc) {
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
