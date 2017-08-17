package com.ep.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.complain.Dimension;
import com.ep.dao.model.complain.ServiceItem;

/**
 * Created by MengWeiBo on 2017-08-07
 */

public interface ComplainMapper {


//    void insert(Dimension dimension);

    List<Dimension> selectDimension();

    void insertDimension(Dimension dimension);

    void updateDimension(Dimension dimension);

    void deleteDimension(@Param("id") Integer id);

    List<ServiceItem> selectItem();

    void insertServiceItem(ServiceItem item);

    void updateServiceItem(ServiceItem item);

    void deleteServiceItem(@Param("id") Integer id);

}
