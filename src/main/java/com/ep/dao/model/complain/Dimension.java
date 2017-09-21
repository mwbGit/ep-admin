package com.ep.dao.model.complain;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-08-09
 */
public class Dimension implements Serializable{
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private String name;
    private Integer ratio;
    private DimensionType type;
    private ProjectType projectType;
    private Integer itemId;
    private ServiceItem item;

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

    public DimensionType getType() {
        return type;
    }

    public void setType(DimensionType type) {
        this.type = type;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public ServiceItem getItem() {
        return item;
    }

    public void setItem(ServiceItem item) {
        this.item = item;
    }
}
