package com.ep.controller.banner;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ep.controller.banner.api.BannerRequest;
import com.ep.controller.banner.api.BannerVO;
import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.util.ApplicationContextUtils;
import com.ep.dao.filter.BannerFilter;
import com.ep.dao.mapper.BannerMapper;
import com.ep.dao.model.banner.Banner;
import com.ep.dao.model.banner.BannerPosition;
import com.ep.dao.model.banner.BannerType;
import com.ep.dao.model.common.Bool;
import com.ep.dao.model.common.PagingFilter;
import com.ep.dao.model.user.User;
import com.ep.service.banner.api.IBannerService;
import com.ep.service.upload.api.IUploadService;

/**
 * Created by MengWeiBo on 2017-10-23
 */

@Controller
@RequestMapping(value = "/banner")
public class BannerController {

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private IBannerService bannerService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse<List<BannerVO>> list(Integer iDisplayStart, Integer iDisplayLength, String positionCode) {

        BannerFilter filter = new BannerFilter();
        filter.setStart(iDisplayStart);
        filter.setSize(iDisplayLength);
        filter.setPosition(BannerPosition.fromCode(positionCode));

        List<Banner> banners = bannerMapper.selectBannerList(filter);
        int count = bannerMapper.countBannerList(filter);


        PagingResponse<List<BannerVO>> response = new PagingResponse<>();
        response.setTotalCount(count);
        response.setAaData(BannerVO.toVOs(banners));

        return response;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public ServiceResponse add(@RequestParam("imgUpload") MultipartFile uploadFile, BannerRequest request) {
        ServiceResponse response = new ServiceResponse();
        String url = uploadService.uploadImage(uploadFile);

        if (url == null) {
            response.setCode("1");
            response.setMessage("上传失败");
            return response;
        }

        Banner banner = new Banner();
        banner.setImg(url);
        banner.setTitle(request.getTitle());
        banner.setLinkUrl(request.getLinkUrl());
        banner.setActivityId(request.getActivityId());
        banner.setOnline(Bool.fromValue(request.getOnline()));
        banner.setType(BannerType.fromCode(request.getTypeCode()));
        banner.setPosition(BannerPosition.fromCode(request.getPositionCode()));
        User user = ApplicationContextUtils.getUser();
        banner.setUpdatedById(user.getId());
        banner.setUpdatedByName(user.getName());
        banner.setUpdateDate(new Date());

        int maxSequence = bannerMapper.selectMaxBannerSequence();
        banner.setSequence(maxSequence);

        bannerMapper.insertBanner(banner);

        return response;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public ServiceResponse modify(@RequestParam("imgUpload") MultipartFile uploadFile, BannerRequest request) {
        ServiceResponse response = new ServiceResponse();

        String url = uploadService.uploadImage(uploadFile);

        Banner banner = new Banner();
        banner.setId(request.getId());
        banner.setImg(url);
        banner.setTitle(request.getTitle());
        banner.setType(BannerType.fromCode(request.getTypeCode()));
        if (banner.getType() == BannerType.ACTIVITY) {
            banner.setActivityId(request.getActivityId());
        } else if (banner.getType() == BannerType.CUSTOM){
            banner.setLinkUrl(request.getLinkUrl());
        }
        banner.setOnline(Bool.fromValue(request.getOnline()));
        User user = ApplicationContextUtils.getUser();
        banner.setUpdatedById(user.getId());
        banner.setUpdatedByName(user.getName());
        banner.setUpdateDate(new Date());

        bannerMapper.updateBanner(banner);

        return response;
    }

    @RequestMapping(value = "/modify/sequence")
    @ResponseBody
    public ServiceResponse modifySequence(@RequestParam Integer id, @RequestParam Boolean asc) {

        bannerService.modifyBannerSequence(id, asc);
        return new ServiceResponse();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestParam Integer id) {

        bannerMapper.deleteBannerById(id);
        return new ServiceResponse();
    }

    @RequestMapping(value = "/publish")
    @ResponseBody
    public ServiceResponse publish(@RequestParam Integer id) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setOnline(Bool.Y);

        bannerMapper.updateBanner(banner);
        return new ServiceResponse();
    }
}
