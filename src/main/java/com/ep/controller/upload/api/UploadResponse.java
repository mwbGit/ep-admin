package com.ep.controller.upload.api;

import com.ep.controller.common.ServiceResponse;

/**
 * Created by MengWeiBo on 2017-10-11
 */
public class UploadResponse  extends ServiceResponse{
    private String originalName = "";
    private String name = "";
    private long size = 0;
    private String state = "";
    private String type = "";
    private String url = "";

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
