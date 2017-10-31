package com.ep.dao.model.advice;

import com.ep.dao.model.common.Bool;

/**
 * Created by 吴晓海 on 2017/10/26.
 */
public class AdviceType {
    private Integer id;
    private String name;
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
