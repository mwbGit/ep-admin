package com.ep.controller.visitor.api;

import com.alibaba.fastjson.JSONObject;
import com.ep.dao.mapper.SqlAdapterMappe;
import com.ep.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 吴晓海 on 2018/1/4.
 */
@Service("VisitorService")
public class VisitorService {

    @Autowired
    private SqlAdapterMappe sqlAdapterMappe;//注入dao

    //获取空间访客信息列表
    public List<Map<String,Object>> getRoomVisitorList(Integer iDisplayStart, Integer iDisplayLength, String sSearch){
        StringBuffer sql = new StringBuffer("SELECT * from t_room_visitor u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql.append(" and u.name like %'"+sSearch+"'%");
        }
        if (StringUtil.isNotBlank(iDisplayStart) && StringUtil.isNotBlank(iDisplayLength)){
            sql.append("limit "+iDisplayStart+","+iDisplayLength+"");
        }
        List<Map<String, Object>> list = sqlAdapterMappe.selectSQL(sql.toString());
        return  list;
    }

    //获取空间访客信息列表数量
    public int getRoomVisitorListCount(String sSearch){
        StringBuffer sql = new StringBuffer("SELECT count(*) from t_room_visitor u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql.append(" and u.name like %'"+sSearch+"'%");
        }
        int count  = sqlAdapterMappe.selectIntSQL(sql.toString());
        return  count;
    }

    //获取客户访客信息列表
    public List<Map<String,Object>> getCustomVisitorList(Integer iDisplayStart, Integer iDisplayLength, String sSearch){
        StringBuffer sql = new StringBuffer("SELECT * from t_custom_visitor u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql.append(" and u.l_name like %'"+sSearch+"'%");
        }
        if (StringUtil.isNotBlank(iDisplayStart) && StringUtil.isNotBlank(iDisplayLength)){
            sql.append("limit "+iDisplayStart+","+iDisplayLength+"");
        }
        List<Map<String, Object>> list = sqlAdapterMappe.selectSQL(sql.toString());
        return  list;
    }

    //获取客户访客信息列表数量
    public int getCustomVisitorListCount(String sSearch){
        StringBuffer sql = new StringBuffer("SELECT count(*) from t_custom_visitor u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql.append(" and u.l_name like %'"+sSearch+"'%");
        }
        int count  = sqlAdapterMappe.selectIntSQL(sql.toString());
        return  count;
    }

    //添加空间访客信息
    public void addRoomVisitor(JSONObject jsonObject){
        StringBuffer sql =new StringBuffer("insert into t_room_visitor(name,call,l_date,l_time,order_time,remark) values ('"+jsonObject.get("name")+"',"+"'"+jsonObject.get("call")+"','"+jsonObject.get("lDate")+"','"+jsonObject.get("lTime")+"','"+jsonObject.get("orderTime")+"','"+jsonObject.get("remark")+"')");

        sqlAdapterMappe.insertSQL(sql.toString());
    }

    //添加客户访客信息
    public void addCustomVisitor(JSONObject jsonObject){
        StringBuffer sql =new StringBuffer("insert into t_custom_visitor(l_name,s_name,s_company_name,car_no,l_date,l_time) values ('"+jsonObject.get("lName")+"',"+"'"+jsonObject.get("sName")+"','"+jsonObject.get("sCompanyName")+"','"+jsonObject.get("carNo")+"','"+jsonObject.get("lDate")+"','"+jsonObject.get("lTime")+"')");

        sqlAdapterMappe.insertSQL(sql.toString());
    }
}
