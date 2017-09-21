package com.ep.dao.filter;

import com.ep.dao.model.common.PagingFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Admin on 2017/9/21.
 */
public class UserFilter extends PagingFilter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {

            this.name = "%" + name + "%";
        }
    }
}
