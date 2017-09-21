package com.ep.dao.model.complain;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-08-09
 */
public class ServiceItem  implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private Integer ratio;
    private ServiceItemType type;

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

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public ServiceItemType getType() {
        return type;
    }

    public void setType(ServiceItemType type) {
        this.type = type;
    }
}
