package com.ep.controller.wx.banner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.wx.banner.api.BannerListResponse;
import com.ep.controller.wx.banner.api.BannerVO;
import com.ep.controller.wx.user.api.UserTokenResponse;
import com.ep.dao.filter.BannerFilter;
import com.ep.dao.mapper.BannerMapper;
import com.ep.dao.model.banner.Banner;
import com.ep.dao.model.banner.BannerPosition;
import com.ep.dao.model.common.Bool;

@RequestMapping(value = "/wx/banner")
@Controller
public class WxBannerController {

    @Autowired
    private BannerMapper bannerMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public BannerListResponse list(@RequestParam String positionCode) {
        BannerListResponse response = new BannerListResponse();

        BannerFilter filter = new BannerFilter();
        filter.setSize(3);
        filter.setStart(0);
        filter.setOnline(Bool.Y);
        filter.setPosition(BannerPosition.fromCode(positionCode));

        List<Banner> banners = bannerMapper.selectBannerList(filter);

        response.setData(BannerVO.toVOs(banners));
        return response;
    }


}
