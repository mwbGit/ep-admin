package com.ep.dao.model.space;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by MengWeiBo on 2017-08-25
 */
public class Space {
    private Integer id;
    private String name;
    private BigDecimal grade;

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

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
