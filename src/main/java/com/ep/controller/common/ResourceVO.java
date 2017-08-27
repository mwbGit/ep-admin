package com.ep.controller.common;

/**
 * Created by MengWeiBo on 2017-08-17
 */
public class ResourceVO  {
    private String label;
    private Object value;

    public ResourceVO() {
    }

    public ResourceVO(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
