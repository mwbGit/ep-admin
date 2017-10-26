package com.ep.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ep.dao.filter.BannerFilter;
import com.ep.dao.model.banner.Banner;
import com.ep.dao.model.common.PagingFilter;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public interface BannerMapper {

    List<Banner> selectBannerList(@Param("filter") BannerFilter filter);

    int countBannerList(@Param("filter") PagingFilter filter);

    void insertBanner(Banner banner);

    void updateBanner(Banner banner);

    void deleteBannerById(@Param("id") Integer id);

    int selectMaxBannerSequence();

    Banner selectBannerById(@Param("id") Integer id);

    public Banner selectBannerBySequence(@Param("sequence") Integer sequence, @Param("asc") boolean asc);

}
