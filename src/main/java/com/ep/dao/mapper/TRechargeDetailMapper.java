package com.ep.dao.mapper;

import com.ep.dao.filter.RechargeFilter;
import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.dao.model.generated.TRechargeDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by fangchen.chai on 2017/8/27
 */
public interface TRechargeDetailMapper extends BaseMapper<TRechargeDetail, Long, TRechargeDetailExample> {

    @Select("select id from t_recharge_detail where sys_order = #{order}")
    Long selectOrderIdByOrder(@Param("order") String order);

    @Update("update t_recharge_detail set pay_status = 2 ,date = now()  where id = #{orderId}")
    Integer updatePayedById(@Param("orderId") Long orderId);
    @Update("update t_recharge_detail set out_pay_code = #{outOrderCode} where id = #{orderId}")
    Integer updateOutPayCodeById(@Param("outOrderCode") String outOrderCode, @Param("orderId") Long orderId);

    List<TRechargeDetail> selectTRechargeDetailByFilter(@Param("filter") RechargeFilter filter);

    Integer countTRechargeDetailByFilter(@Param("filter") RechargeFilter filter);
}
