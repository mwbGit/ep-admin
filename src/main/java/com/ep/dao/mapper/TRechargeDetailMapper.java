package com.ep.dao.mapper;

import com.ep.dao.model.generated.TRechargeDetail;
import com.ep.dao.model.generated.TRechargeDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by fangchen.chai on 2017/8/27
 */
public interface TRechargeDetailMapper extends BaseMapper<TRechargeDetail, Long, TRechargeDetailExample> {

    @Select("select id from t_recharge_detail where order = #{order}")
    Long selectOrderIdByOrder(@Param("order") String order);

    @Update("update table t_recharge_detail set status = 2 and date = now()  where id = #{orderId}")
    Integer setPayedById(@Param("orderId") Long orderId);
    @Update("update table t_recharge_detail set out_pay_code = #{outOrderCode} where id = #{orderId}")
    Integer setOutPayCodeById(@Param("outOrderCode") String outOrderCode, @Param("orderId") Long orderId);

}
