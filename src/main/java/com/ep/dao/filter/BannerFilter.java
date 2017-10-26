package com.ep.dao.filter;

import com.ep.dao.model.banner.BannerPosition;
import com.ep.dao.model.banner.BannerType;
import com.ep.dao.model.common.PagingFilter;

/**
 * Created by Admin on 2017/9/21.
 */
public class BannerFilter extends PagingFilter {
    private BannerPosition position;

    public BannerPosition getPosition() {
        return position;
    }

    public void setPosition(BannerPosition position) {
        this.position = position;
    }
}
