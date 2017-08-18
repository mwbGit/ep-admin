package com.ep.controller.complain.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.dao.model.complain.Dimension;

/**
 * Created by MengWeiBo on 2017-08-18
 */
public class DimensionVO {
    private Integer id;
    private String name;
    private Integer ratio;
    private String type;

    public static List<DimensionVO> toVOs(List<Dimension> dimensions) {
        List<DimensionVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dimensions)) {
            for (Dimension dimension : dimensions) {
                DimensionVO vo = new DimensionVO();
                vo.setId(dimension.getId());
                vo.setName(dimension.getName());
                vo.setRatio(dimension.getRatio());
                vo.setType(dimension.getType().getDescription());

                vos.add(vo);
            }
        }

        return vos;
    }
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
