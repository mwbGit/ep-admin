package com.ep.dao.filter;

import org.apache.commons.lang3.StringUtils;

import com.ep.dao.model.common.PagingFilter;

/**
 * Created by Admin on 2017/9/21.
 */
public class ActivityFilter extends PagingFilter {
    private String title;
    private Integer typeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (StringUtils.isNotBlank(title)) {
            this.title = "%" + title + "%";
        }
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
