package com.ep.controller.wx.information.api;

/**
 * Created by 吴晓海 on 2017/11/9.
 */
public class PageRequest {
    private Integer themeid;
    private Integer count;
    private String type;

    public Integer getThemeid() {
        return themeid;
    }

    public void setThemeid(Integer themeid) {
        this.themeid = themeid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
