package com.ep.service.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.mapper.BannerMapper;
import com.ep.dao.model.banner.Banner;
import com.ep.service.banner.api.IBannerService;

/**
 * Created by MengWeiBo on 2017-08-07
 */

@Service
public class BannerService implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public void modifyBannerSequence(Integer id, Boolean asc) {

        Banner banner = bannerMapper.selectBannerById(id);

        Banner replaceBanner = bannerMapper.selectBannerBySequence(banner.getSequence(), asc);

        int sequence = banner.getSequence();
        banner.setSequence(replaceBanner.getSequence());
        replaceBanner.setSequence(sequence);

        bannerMapper.updateBanner(banner);

        bannerMapper.updateBanner(replaceBanner);
    }
}
