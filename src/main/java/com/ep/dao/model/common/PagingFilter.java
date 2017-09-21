package com.ep.dao.model.common;

public class PagingFilter {

    private Integer start;
    private Integer size;

    public PagingFilter(Integer start, Integer size) {
        this.start = start;
        this.size = size;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
