package com.ep.dao.model.communit;

import com.ep.dao.model.common.Bool;

import java.io.Serializable;

/**
 * Created by mengweibo on 2017/12/12.
 */
public class CommunityDevice implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private String img;
    private Integer sequence;
    private Bool deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Bool getDeleted() {
        return deleted;
    }

    public void setDeleted(Bool deleted) {
        this.deleted = deleted;
    }
}
