package com.ep.dao.model.banner;

import java.util.HashMap;
import java.util.Map;

import com.ep.dao.model.common.IdInterface;

/**
 * Created by MengWeiBo on 2017-09-07
 */
public enum BannerType implements IdInterface {
    ACTIVITY(1, "ACTIVITY", "活动详情"),
    CUSTOM(2, "CUSTOM", "自定义"),
    NONE(3, "NONE", "无");

    private static final Map<String, BannerType> code2BannerTypes;

    private final int id;
    private final String code;
    private final String description;

    static {
        code2BannerTypes = new HashMap<>();

        for(BannerType bannerType : BannerType.values()){
            code2BannerTypes.put(bannerType.getCode(), bannerType);
        }
    }

    public static BannerType fromCode(String code){
        return code2BannerTypes.get(code);
    }

    BannerType(int id, String code, String description){
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

