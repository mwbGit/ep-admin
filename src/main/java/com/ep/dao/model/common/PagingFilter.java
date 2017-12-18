package com.ep.dao.model.common;

public class PagingFilter {

    private Integer start;
    private Integer size;

    public PagingFilter() {
    }

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
        if (size != null && size < 0) {
            return null;
        }
        return size;
    }

    public void setSize(Integer size) {
        if (size > 0) {
            this.size = size;
        }
    }
}
