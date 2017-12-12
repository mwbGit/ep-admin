package com.ep.controller.community.api;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-09-21
 */
public class CommunityDeviceVO implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String img;
    private String name;
    private Integer sequence;
    private Boolean first;
    private Boolean end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }
}
