package com.ep.controller.community;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.community.api.CommunityDeviceVO;
import com.ep.dao.mapper.CommunityMapper;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.communit.CommunityDevice;
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

        List<CommunityDevice> devices = communityMapper.selectCommunityDeviceList(new PagingFilter(iDisplayStart, iDisplayLength));
        int count = communityMapper.countCommunityDeviceList(new PagingFilter(iDisplayStart, iDisplayLength));

        List<CommunityDeviceVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(devices)) {

            if (CollectionUtils.isNotEmpty(devices)) {
                for (int i = 0; i < devices.size(); i++) {
                    CommunityDevice device = devices.get(i);
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

        CommunityDevice communityDevice = new CommunityDevice();
        communityDevice.setId(id);
        communityDevice.setDeleted(Bool.Y);

        communityMapper.updateCommunityDevice(communityDevice);

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

        int count = communityMapper.countCommunityDeviceByName(name, id);
        if (count > 0) {
            response.setCode("1");
            response.setMessage("名称重复");
            return response;
        }

        CommunityDevice communityDevice = new CommunityDevice();
        communityDevice.setId(id);
        communityDevice.setName(name);
        communityDevice.setImg(url);

        communityMapper.updateCommunityDevice(communityDevice);


        return response;
    }

    @RequestMapping(value = "/device/modify/order")
    @ResponseBody
    public ServiceResponse deviceModifyOrder(@RequestParam(value = "id") Integer id, Boolean asc) {
        ServiceResponse response = new ServiceResponse();

        communityService.modifyCommunityDeviceSequence(id, asc);

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

        int count = communityMapper.countCommunityDeviceByName(name, null);
        if (count > 0) {
            response.setCode("2");
            response.setMessage("名称重复");
            return response;
        }

        int sequence = communityMapper.selectMaxCommunityDeviceSequence();

        CommunityDevice communityDevice = new CommunityDevice();
        communityDevice.setName(name);
        communityDevice.setImg(url);
        communityDevice.setDeleted(Bool.N);
        communityDevice.setSequence(sequence);

        communityMapper.insertCommunityDevice(communityDevice);

        return response;
    }

}
