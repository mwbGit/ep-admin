package com.ep.controller.information;

import com.ep.controller.common.PagingResponse;
import com.ep.controller.common.ServiceResponse;
import com.ep.controller.information.api.InformationTypeVo;
import com.ep.controller.information.api.InformationVo;
import com.ep.controller.resource.api.ResourceResponse;
import com.ep.controller.resource.api.ResourceVO;
import com.ep.controller.wx.information.api.InformationVO;
import com.ep.dao.mapper.SqlAdapterMappe;
import com.ep.dao.model.advice.api.AddAdviceRequest;
import com.ep.dao.model.common.Bool;
import com.ep.service.adviceService.AdviceService;
import com.ep.service.upload.api.IUploadService;
import com.ep.util.DateTimeUtility;
import com.ep.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/advice")
@Controller
public class InformationController {


    @Resource
    private AdviceService adviceService;
    @Autowired
    private SqlAdapterMappe sqlAdapterMappe;//注入dao
    @Autowired
    private IUploadService uploadService;

    //获取资讯列表
    @RequestMapping(value = "/list")
    @ResponseBody
    public PagingResponse<List<InformationVo>> list(Integer iDisplayStart, Integer iDisplayLength, String sSearch, Integer typeId) {
        PagingResponse<List<InformationVo>> response = new PagingResponse<>();
        //获取资讯列表
        StringBuffer sql = new StringBuffer("SELECT * from t_advice u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql.append(" and u.title like %'"+sSearch+"'%");
        }
        if (StringUtil.isNotBlank(typeId)){
            sql.append(" and u.title like %'"+typeId+"'%");
        }

        if (StringUtil.isNotBlank(iDisplayStart) && StringUtil.isNotBlank(iDisplayLength)){
            sql.append("order by u.createTime desc limit "+iDisplayStart+","+iDisplayLength+"");
        }
        List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
        //获取资讯数量
        StringBuffer sql2 = new StringBuffer("SELECT count(*) from t_advice u WHERE 1=1 ");
        if (StringUtil.isNotBlank(sSearch)){
            sql2.append(" and u.title like %'"+sSearch+"'%");
        }
        if (StringUtil.isNotBlank(typeId)){
            sql2.append(" and u.title like %'"+typeId+"'%");
        }
        int count = sqlAdapterMappe.selectIntSQL(sql2.toString());
        //资讯数据封装到集合
        List<InformationVo> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(types)) {
            for (Map map : types) {
                InformationVo vo = new InformationVo();
                Date date = (Date) map.get("createTime");
                vo.setId((int)map.get("id"));
                vo.setTitle((String)map.get("title"));
                vo.setTypeId((String)map.get("typeId"));
                vo.setMiniText((String)map.get("miniText"));
                vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMM(date));

                vos.add(vo);
            }
        }
        response.setTotalCount(count);
        response.setAaData(vos);

        return response;
    }

        //获取资讯类别列表
        @RequestMapping(value = "/get/type/list")
        @ResponseBody
        public ResourceResponse activityTypeList() {
            List<ResourceVO> vos = new ArrayList<>();
            //获取资讯类别
            StringBuffer sql = new StringBuffer("SELECT * FROM t_advice_type u WHERE u.is_deleted='N'");

            List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
            //资讯类别封装到集合
            if (CollectionUtils.isNotEmpty(types)) {
                for (Map map : types) {
                    ResourceVO vo = new ResourceVO();
                    vo.setLabel((String)map.get("name"));
                    vo.setValue((int)map.get("id"));

                    vos.add(vo);
                }
            }

            return new ResourceResponse(vos);
        }

    //添加资讯
    @RequestMapping(value = "/add")
    @ResponseBody
    public ServiceResponse add(@RequestParam("imgUpload") MultipartFile uploadFile,@RequestParam("imgUpload2") MultipartFile uploadFile2,@RequestParam("imgUpload3") MultipartFile uploadFile3, AddAdviceRequest addAdviceRequest ) {
        ServiceResponse response = new ServiceResponse();
        //图片url
        String url = uploadService.uploadImage(uploadFile);
        String url2 = uploadService.uploadImage(uploadFile2);
        String url3 = uploadService.uploadImage(uploadFile3);
        if (StringUtil.isNotBlank(url2)){
            url=url+","+url2;
        }if (StringUtil.isNotBlank(url3)){
            url=url+","+url3;
        }
        //id存在更新资讯信息，为null添加资讯
        if (addAdviceRequest.getId()!=null){
            if(url==null){
                StringBuffer sql =new StringBuffer("update t_advice set createTime = NOW(),content = '"+addAdviceRequest.getContent()+"',title= '"+addAdviceRequest.getTitle()+"' , minitext ='"+addAdviceRequest.getMiniText()+"' ,typeId='"+addAdviceRequest.getTypeId()+"' " +" where id = '"+addAdviceRequest.getId()+"'");
                sqlAdapterMappe.updateSQL(sql.toString());
            }else {
                StringBuffer sql =new StringBuffer("update t_advice set createTime = NOW(),content = '"+addAdviceRequest.getContent()+"',title= '"+addAdviceRequest.getTitle()+"' , minitext ='"+addAdviceRequest.getMiniText()+"' ,typeId='"+addAdviceRequest.getTypeId()+"',img = '"+url +"' where id = '"+addAdviceRequest.getId()+"'");
                sqlAdapterMappe.updateSQL(sql.toString());
            }
        }else {
            StringBuffer sql =new StringBuffer("insert into t_advice(createTime,content,title,minitext,typeId,img) values (NOW(),'"+addAdviceRequest.getContent()+"','"+addAdviceRequest.getTitle()+"',"+"'"+addAdviceRequest.getMiniText()+"','"+addAdviceRequest.getTypeId()+"','"+url+"')");

            sqlAdapterMappe.insertSQL(sql.toString());
            //todo mapper count
        }



        return response;
    }

    //获取资讯类别列表
    @RequestMapping(value = "/type/list")
    @ResponseBody
    public PagingResponse<List<InformationTypeVo>> typeList(Integer iDisplayStart, Integer iDisplayLength) {
       if (iDisplayStart==null){
           iDisplayStart=0;
           iDisplayLength=10000;
       }
        //获取资讯类别列表
        StringBuffer sql = new StringBuffer("SELECT * FROM t_advice_type u WHERE u.is_deleted='N' ORDER BY u.sequence ASC");
        if (StringUtil.isNotBlank(iDisplayStart)&&StringUtil.isNotBlank(iDisplayLength)){
            sql.append(" limit "+iDisplayStart+","+iDisplayLength+"");
        }
        List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
        //获取资讯类别数量
        StringBuffer sql2 = new StringBuffer("SELECT COUNT(*) FROM t_advice_type u WHERE u.is_deleted='N'");
        int count = sqlAdapterMappe.selectIntSQL(sql2.toString());
        int i = 0;
        //封装list
        List<InformationTypeVo> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(types)) {

            if (CollectionUtils.isNotEmpty(types)) {
                for (Map map:types) {
                    //AdviceType type = types.get(i);
                    InformationTypeVo vo = new InformationTypeVo();

                    vo.setId((int)map.get("id"));
                    vo.setName((String)map.get("name"));
                    vo.setSequence(iDisplayStart + i + 1);
                    if (i == 0) {
                        vo.setFirst(true);
                    } else {
                        vo.setFirst(false);
                    }

                    if (i == types.size() - 1) {
                        vo.setEnd(true);
                    } else {
                        vo.setEnd(false);
                    }

                    vos.add(vo);
                    i++;
                }
            }
        }

        PagingResponse<List<InformationTypeVo>> response = new PagingResponse<>();
        response.setTotalCount(count);
        response.setAaData(vos);

        return response;
    }

    //资讯类别排序
    @RequestMapping(value = "/type/modify")
    @ResponseBody
    public ServiceResponse typpModify(@RequestParam(value = "id") Integer id, String name, Boolean asc, Integer sequence) {
        ServiceResponse response = new ServiceResponse();

        if (asc == null) {
            StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM t_advice_type u WHERE u.name='"+name+"'");
            int count = sqlAdapterMappe.selectIntSQL(sql.toString());
            //int count = activityMapper.countActivityByName(name, id);
            if (count > 0) {
                response.setCode("1");
                response.setMessage("名称重复");
                return response;
            }

            StringBuffer sql2 = new StringBuffer("update t_advice_type t set t.name= '"+name+"' where t.id ='"+id+"'");

            sqlAdapterMappe.updateSQL(sql2.toString());
            //activityMapper.updateActivityType(activityType);
        } else {
            //当前序列下移
            if (asc==false){
                int sequenceChance = sequence+1;
                StringBuffer sql3 = new StringBuffer("update t_advice_type t set t.sequence= '"+sequence+"' where t.sequence ='"+sequenceChance+"'");
                StringBuffer sql4 = new StringBuffer("update t_advice_type t set t.sequence= '"+sequenceChance+"' where t.id ='"+id+"'");
                sqlAdapterMappe.updateSQL(sql3.toString());
                sqlAdapterMappe.updateSQL(sql4.toString());
            }else {
                //当前序列上移
                int sequenceChance = sequence-1;
                StringBuffer sql3 = new StringBuffer("update t_advice_type t set t.sequence= '"+sequence+"' where t.sequence ='"+sequenceChance+"'");
                StringBuffer sql4 = new StringBuffer("update t_advice_type t set t.sequence= '"+sequenceChance+"' where t.id ='"+id+"'");
                sqlAdapterMappe.updateSQL(sql3.toString());
                sqlAdapterMappe.updateSQL(sql4.toString());
            }
            //activityService.modifyActivityTypeSequence(id, asc);
        }

        return response;
    }

    //添加资讯类别
    @RequestMapping(value = "/type/add")
    @ResponseBody
    public ServiceResponse typeAdd(@RequestParam(value = "name") String name) {
        ServiceResponse response = new ServiceResponse();
        //验证添加资讯类别名称是否为空
        if (!StringUtils.isBlank(name)) {
            response.setCode("1");
            response.setMessage("名称为空");
            return response;
        }
        //验证添加资讯类别名称是否重复
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM t_advice_type u WHERE u.name='"+name+"'");
        int count = sqlAdapterMappe.selectIntSQL(sql.toString());
        //int count = activityMapper.countActivityByName(name, id);
        if (count > 0) {
            response.setCode("2");
            response.setMessage("名称重复");
            return response;
        }
        //名称不为空且不重复添加资讯类别
        StringBuffer sql2 = new StringBuffer("SELECT COUNT(*) FROM t_advice_type u ");
        int maxcount = sqlAdapterMappe.selectIntSQL(sql2.toString());
        int sequence = maxcount+1;
        //int sequence = activityMapper.selectMaxActivitySequence();
        StringBuffer sql3 = new StringBuffer("insert into t_advice_type (name,sequence,is_deleted) values ('"+name+"','"+sequence+"',"+"'"+Bool.N+"')");
        sqlAdapterMappe.insertSQL(sql3.toString());
        return response;
    }

    //删除资讯类别
    @RequestMapping(value = "/type/delete")
    @ResponseBody
    public ServiceResponse typeDelete(@RequestParam(value = "id") Integer id) {
        StringBuffer sql = new StringBuffer("update t_advice_type t set t.is_deleted= '"+Bool.Y+"' where t.id ='"+id+"'");
        sqlAdapterMappe.updateSQL(sql.toString());
        return new ServiceResponse();
    }

    //删除资讯
    @RequestMapping(value = "/manger/delete")
    @ResponseBody
    public ServiceResponse Delete(@RequestParam(value = "id") Integer id) {
        StringBuffer sql = new StringBuffer("DELETE FROM t_advice where id ="+id+"");
        sqlAdapterMappe.deleteSQL(sql.toString());
        return new ServiceResponse();
    }

    //资讯修改页面信息返显
    @RequestMapping(value = "/detail")
    @ResponseBody
    public List detail(@RequestParam("id") Integer id) throws Exception {
        InformationVo response = new InformationVo();
        //获取id资讯信息
        StringBuffer sql = new StringBuffer("select * from t_advice where id = "+id+"");
        List<Map<String, Object>> types = sqlAdapterMappe.selectSQL(sql.toString());
       /* Map map = types.get(0);
        response.setMiniText((String)map.get("miniText"));
        response.setTypeId((String)map.get("typeId"));
        response.setContent((String)map.get("content"));
        response.getImgUpload(map.get("img").toString());
        response.setTitle((String)map.get("title"));*/

        return InformationVo.toVOs(types);
    }
}
