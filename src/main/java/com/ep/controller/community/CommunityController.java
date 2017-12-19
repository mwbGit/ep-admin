package com.ep.controller.community;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.community.api.CommunityDeviceVO;
import com.ep.controller.community.api.CommunityInfoResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.community.Community;
import com.ep.dao.model.community.Device;
import com.ep.dao.model.user.User;
import com.ep.service.community.api.ICommunityService;
import com.ep.service.upload.api.IUploadService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/community")
@Controller
public class CommunityController {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private ICommunityService communityService;

    @Autowired
    private IUploadService uploadService;

    @RequestMapping(value = "/device/list")
    @ResponseBody
    public PagingResponse<List<CommunityDeviceVO>> deviceList(Integer iDisplayStart, Integer iDisplayLength) {

        List<Device> devices = communityMapper.selectDeviceList(new PagingFilter(iDisplayStart, iDisplayLength));
        int count = communityMapper.countDeviceList(new PagingFilter(iDisplayStart, iDisplayLength));

        List<CommunityDeviceVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(devices)) {

            if (CollectionUtils.isNotEmpty(devices)) {
                if (iDisplayStart == null) {
                    iDisplayStart = 0;
                }

                for (int i = 0; i < devices.size(); i++) {
                    Device device = devices.get(i);
                    CommunityDeviceVO vo = new CommunityDeviceVO();

                    vo.setId(device.getId());
                    vo.setName(device.getName());
                    vo.setImg(device.getImg());
                    vo.setSequence(iDisplayStart + i + 1);
                    if (i == 0) {
                        vo.setFirst(true);
                    } else {
                        vo.setFirst(false);
                    }

                    if (i == devices.size() - 1) {
                        vo.setEnd(true);
                    } else {
                        vo.setEnd(false);
                    }

                    vos.add(vo);
                }
            }
        }

        PagingResponse<List<CommunityDeviceVO>> response = new PagingResponse<>();
        response.setTotalCount(count);
        response.setAaData(vos);

        return response;
    }

    @RequestMapping(value = "/device/delete")
    @ResponseBody
    public ServiceResponse deviceDelete(@RequestParam(value = "id") Integer id) {

        Device device = new Device();
        device.setId(id);
        device.setDeleted(Bool.Y);

        communityMapper.updateDevice(device);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/device/modify")
    @ResponseBody
    public ServiceResponse deviceModify(@RequestParam(value = "imgUpload", required = false) MultipartFile imgUpload,
                                        @RequestParam(value = "id") Integer id, String name) {
        ServiceResponse response = new ServiceResponse();
        String url = null;
        if (imgUpload != null && !imgUpload.isEmpty()) {
            url = uploadService.uploadImage(imgUpload);
        }

        int count = communityMapper.countDeviceByName(name, id);
        if (count > 0) {
            response.setCode("1");
            response.setMessage("名称重复");
            return response;
        }

        Device device = new Device();
        device.setId(id);
        device.setName(name);
        device.setImg(url);

        communityMapper.updateDevice(device);


        return response;
    }

    @RequestMapping(value = "/device/modify/order")
    @ResponseBody
    public ServiceResponse deviceModifyOrder(@RequestParam(value = "id") Integer id, Boolean asc) {
        ServiceResponse response = new ServiceResponse();

        communityService.modifyDeviceSequence(id, asc);

        return response;
    }

    @RequestMapping(value = "/device/add")
    @ResponseBody
    public ServiceResponse deviceAdd(@RequestParam("imgUpload") MultipartFile uploadFile,
                                     @RequestParam(value = "name") String name) {
        ServiceResponse response = new ServiceResponse();
        if (StringUtils.isBlank(name)) {
            response.setCode("1");
            response.setMessage("名称为空");
            return response;
        }

        String url = uploadService.uploadImage(uploadFile);

        if (url == null) {
            response.setCode("1");
            response.setMessage("上传失败");
            return response;
        }

        int count = communityMapper.countDeviceByName(name, null);
        if (count > 0) {
            response.setCode("2");
            response.setMessage("名称重复");
            return response;
        }

        int sequence = communityMapper.selectMaxDeviceSequence();

        Device device = new Device();
        device.setName(name);
        device.setImg(url);
        device.setDeleted(Bool.N);
        device.setSequence(sequence);

        communityMapper.insertDevice(device);

        return response;
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public CommunityInfoResponse detail(@RequestParam(value = "id") Integer id) {
        CommunityInfoResponse response = new CommunityInfoResponse();

        Community community = communityService.getCommunity(id);

        if (community != null) {
            response.setName(community.getName());
            response.setTag(community.getTag());
            response.setTips(community.getTips());
            response.setContent(community.getContent());
            response.setUserIds(community.getUserIds());
            response.setDevices(community.getDevices());
            response.setPictures(community.getPictures());
            response.setStationTotal(community.getStationTotal());
            response.setRentNum(community.getRentNum());
            response.setSurplusNum(community.getSurplusNum());
            response.setUserIds(community.getUserIds());
            response.setDevices(community.getDevices());
            response.setPictures(community.getPictures());
        }

        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse list(Integer iDisplayStart, Integer iDisplayLength) {
        PagingResponse<List<Community>> response = new PagingResponse<>();

        List<Community> communities = communityMapper.selectCommunityList(new PagingFilter(iDisplayStart, iDisplayLength));
        int count = communityMapper.countCommunityList(new PagingFilter(iDisplayStart, iDisplayLength));

        response.setAaData(communities);
        response.setTotalCount(count);

        return response;
    }

    @RequestMapping(value = "/publish")
    @ResponseBody
    public ServiceResponse publish(Integer id) {
        ServiceResponse response = new ServiceResponse();

        Community community = communityService.getCommunity(id);
        if (StringUtils.isBlank(community.getTips())) {
            response.setCode("1");
            response.setMessage("请完善资料, tips!");
        } else if (StringUtils.isBlank(community.getContent())) {
            response.setCode("2");
            response.setMessage("请完善资料, 社区介绍!");
        } else if (CollectionUtils.isEmpty(community.getPictures())) {
            response.setCode("3");
            response.setMessage("请完善资料, 社区图片!");
        } else if (CollectionUtils.isEmpty(community.getDevices())) {
            response.setCode("4");
            response.setMessage("请完善资料, 社区设施!");
        } else if (CollectionUtils.isEmpty(community.getUserIds())) {
            response.setCode("5");
            response.setMessage("请完善资料, 社区经理!");
        } else {
            User user = ApplicationContextUtils.getUser();

            Community upCommunity = new Community();
            upCommunity.setId(id);
            upCommunity.setUpdatedById(user.getId());
            upCommunity.setUpdatedByName(user.getName());

            communityMapper.updateCommunity(community);
        }

        return response;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ServiceResponse add(@RequestParam(value = "imgUploads", required = false) MultipartFile[] uploadFiles,
                               Community community) {
        ServiceResponse response = new ServiceResponse();

        User user = ApplicationContextUtils.getUser();
        community.setUpdatedById(user.getId());
        community.setUpdatedByName(user.getName());

        List<String> pictures = new ArrayList<>();
        if (uploadFiles != null) {
            for (MultipartFile file : uploadFiles) {
                String url = uploadService.uploadImage(file);
                if (url != null) {
                    pictures.add(url);
                }
            }
            community.setPictures(pictures);
        }


        communityService.addCommunity(community);

        return response;
    }
}
