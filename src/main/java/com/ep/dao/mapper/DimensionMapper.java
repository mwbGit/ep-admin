package com.ep.dao.mapper;

import java.util.List;

import com.ep.dao.model.complain.Dimension;

/**
 * Created by MengWeiBo on 2017-08-07
 */

public interface DimensionMapper {


//    void insert(Dimension dimension);

    List<Dimension> selectDimension();
}
