package com.ep.controller.resource.api;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-10-13
 */
public class ResourceResponse {
    private List<ResourceVO> data;

    public ResourceResponse(List<ResourceVO> data) {
        this.data = data;
    }

    public List<ResourceVO> getData() {
        return data;
    }

    public void setData(List<ResourceVO> data) {
        this.data = data;
    }
}
