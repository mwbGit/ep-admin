package com.ep.dao.mapper;

import com.ep.dao.filter.ConfigRechargeFilter;
import com.ep.dao.model.generated.TConfigRecharge;
import com.ep.dao.model.generated.TConfigRechargeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther fangchen.chai ON 2017/12/2
 */
public interface TConfigRechargeMapper extends BaseMapper<TConfigRecharge,Long,TConfigRechargeExample>{

    List<TConfigRecharge> selectTConfigRechargeByFilter(@Param("filter") ConfigRechargeFilter filter);

    Integer countTConfigRechargeByFilter(@Param("filter") ConfigRechargeFilter filter);


}
