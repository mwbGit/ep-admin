package com.ep.controller.common;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-03-28
 */
public class PagingResponse<T> extends ServiceResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int iTotalDisplayRecords;
    private int iTotalRecords;
    private int totalCount;
    private T aaData;
    private T data;

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.iTotalRecords = totalCount;
        this.iTotalDisplayRecords = totalCount;
    }

    public T getAaData() {
        return aaData;
    }

    public void setAaData(T aaData) {
        this.aaData = aaData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
