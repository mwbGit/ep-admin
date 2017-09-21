package com.ep.dao.mapper;

import java.util.List;

import com.ep.dao.filter.ComplainFilter;
import org.apache.ibatis.annotations.Param;

import com.ep.dao.model.complain.Complain;
import com.ep.dao.model.complain.Dimension;
import com.ep.dao.model.complain.ServiceItem;

/**
 * Created by MengWeiBo on 2017-08-07
 */

public interface ComplainMapper {

    List<Complain> selectComplain(@Param("filter") ComplainFilter filter);

    int countComplain(@Param("filter") ComplainFilter filter);

    void deleteComplain(@Param("dimensionId") Integer dimensionId, @Param("itemId") Integer itemId);

    List<Dimension> selectDimension(@Param("itemId") Integer itemId);

    void insertDimension(Dimension dimension);

    void updateDimension(Dimension dimension);

    void deleteDimensionById(@Param("id") Integer id);

    void deleteDimensionByItemId(@Param("itemId") Integer itemId);

    List<ServiceItem> selectItem();

    void insertServiceItem(ServiceItem item);

    void updateServiceItem(ServiceItem item);

    void deleteServiceItem(@Param("id") Integer id);

}
