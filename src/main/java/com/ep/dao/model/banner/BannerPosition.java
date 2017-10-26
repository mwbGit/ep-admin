package com.ep.dao.model.banner;

import java.util.HashMap;
import java.util.Map;

import com.ep.dao.model.common.IdInterface;

/**
 * Created by MengWeiBo on 2017-09-07
 */
public enum BannerPosition implements IdInterface {
    HOME_PAGE(1, "HOME_PAGE", "首页轮播"),
    E_CARD(2, "E_CARD", "E卡管理轮播");

    private static final Map<String, BannerPosition> code2BannerPositions;

    private final int id;
    private final String code;
    private final String description;

    static {
        code2BannerPositions = new HashMap<>();

        for(BannerPosition bannerType : BannerPosition.values()){
            code2BannerPositions.put(bannerType.getCode(), bannerType);
        }
    }

    public static BannerPosition fromCode(String code){
        return code2BannerPositions.get(code);
    }

    BannerPosition(int id, String code, String description){
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

}

