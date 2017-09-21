package com.ep.dao.filter;

import com.ep.dao.model.common.PagingFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Admin on 2017/9/21.
 */
public class ComplainFilter extends PagingFilter {
    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
