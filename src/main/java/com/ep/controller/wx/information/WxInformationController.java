package com.ep.controller.wx.information;


import com.ep.controller.wx.information.api.InformationInfoTopResponse;
import com.ep.controller.wx.information.api.InformationVO;
import com.ep.controller.wx.information.api.PageRequest;
import com.ep.dao.mapper.SqlAdapterMappe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by 吴晓海 on 2017/11/9.
 */
@RequestMapping(value = "/wx/information")
@Controller
public class WxInformationController {

    @Autowired
    private SqlAdapterMappe sqlAdapterMappe;//注入dao

    //获取当前id资讯信息
    @RequestMapping(value = "/detail")
    @ResponseBody
    public List detail(@RequestParam Integer id) {
        InformationInfoTopResponse response = new InformationInfoTopResponse();
        //获取id资讯信息
        StringBuffer sql = new StringBuffer("select * from t_advice where id = "+id+"");
        List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
        //更新点击数
        int sum = (int)types.get(0).get("clicksum")+1;
        StringBuffer sql2 = new StringBuffer("update t_advice set clicksum = '"+sum+"' where id = "+id+"");
        sqlAdapterMappe.updateSQL(sql2.toString());
        return types;
    }

    //首页资讯
    @RequestMapping(value = "/topInformation")
    @ResponseBody
    public InformationInfoTopResponse topInformation(@RequestParam Integer id) {
        InformationInfoTopResponse response = new InformationInfoTopResponse();
        //获取前三条资讯信息
        StringBuffer sql = new StringBuffer("select * from t_advice limit 3;");
        List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
        response.setData(InformationVO.toVOs(types));
        return response;
    }

    //获取当前页面资讯
    @RequestMapping(value = "/list")
    @ResponseBody
    public InformationInfoTopResponse list(@RequestBody PageRequest request) {
        InformationInfoTopResponse response = new InformationInfoTopResponse();
        String type = request.getType();
        int themeid = request.getThemeid();
        int count = request.getCount();
        //获取页面资讯信息
        if (type.equals("before")){
            StringBuffer sql = new StringBuffer("SELECT * FROM t_advice WHERE id <="+themeid+" ORDER BY id DESC LIMIT "+count+";");
            List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
            response.setData(InformationVO.toVOs(types));
        }else if (type.equals("laster")){
            StringBuffer sql = new StringBuffer("SELECT * FROM (SELECT * FROM t_advice WHERE id >="+themeid+" ORDER BY id ASC LIMIT "+count+") t ORDER BY id DESC;");
            List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
            response.setData(InformationVO.toVOs(types));
        }
        return response;
    }


}
