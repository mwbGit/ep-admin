package com.ep.dao.model.complain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ep.dao.model.common.IdInterface;

public enum ProjectType implements IdInterface {
    ACTIVE(1, "ACTIVE", "Active"),
    LOCKED(2, "LOCKED", "Locked"),
    SUSPENDED(3, "SUSPENDED", "Suspended"),
    CANCELLED(4, "CANCELLED", "Cancelled");

    private static final Map<String, ProjectType> code2DimensionTypes;
    private static final List<Integer> unQuitStatusIds;

    private int id;
    private String code;
    private String description;

    static {
    	code2DimensionTypes = new HashMap<String, ProjectType>();

        for (ProjectType DimensionTypes : ProjectType.values()) {
        	code2DimensionTypes.put(DimensionTypes.getCode(), DimensionTypes);
        }

        unQuitStatusIds = new ArrayList<Integer>();

        unQuitStatusIds.add(ACTIVE.getId());
        unQuitStatusIds.add(LOCKED.getId());
        unQuitStatusIds.add(SUSPENDED.getId());
    }

    public static ProjectType fromCode(String code) {
        return code2DimensionTypes.get(code);
    }

    ProjectType(int id, String code, String description) {
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
