package com.ep.dao.model.complain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ep.dao.model.common.IdInterface;

public enum ServiceItemType implements IdInterface {
    ACTIVE(1, "ACTIVE", "服务项1"),
    LOCKED(2, "LOCKED", "服务项2"),
    SUSPENDED(3, "SUSPENDED", "服务项3"),
    CANCELLED(4, "CANCELLED", "服务项4");

    private static final Map<String, ServiceItemType> code2DimensionTypes;
    private static final List<Integer> unQuitStatusIds;

    private int id;
    private String code;
    private String description;

    static {
    	code2DimensionTypes = new HashMap<String, ServiceItemType>();

        for (ServiceItemType DimensionTypes : ServiceItemType.values()) {
        	code2DimensionTypes.put(DimensionTypes.getCode(), DimensionTypes);
        }

        unQuitStatusIds = new ArrayList<Integer>();

        unQuitStatusIds.add(ACTIVE.getId());
        unQuitStatusIds.add(LOCKED.getId());
        unQuitStatusIds.add(SUSPENDED.getId());
    }

    public static ServiceItemType fromCode(String code) {
        return code2DimensionTypes.get(code);
    }

    ServiceItemType(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
	public static List<Integer> getUnCancelledStatusIds() {
		return unQuitStatusIds;
	}
	
}
