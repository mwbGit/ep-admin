package com.ep.controller.information.api;


import com.ep.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * Created by 吴晓海 on 2017/10/24.
 */
public class InformationVo {
    private Integer id;
    private String title;
    private String miniText;
    private String imgUpload;
    private String createTime;
    private String content;
    private String typeId;
    private  String[] imgs;

    public static List<InformationVo> toVOs(List<Map<String, Object>> list) {
        List<InformationVo> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map map : list) {
                InformationVo vo = new InformationVo();
                if(StringUtil.isNotBlank(map.get("title"))){
                    vo.setTitle((String)map.get("title"));
                }if(StringUtil.isNotBlank(map.get("miniText"))){
                    vo.setMiniText((String)map.get("miniText"));
                }if(StringUtil.isNotBlank(map.get("img"))){
                    vo.setImgs(((String) map.get("img")).split(","));
                }if(StringUtil.isNotBlank(map.get("typeId"))){
                    vo.setTypeId((String)map.get("typeId"));
                }if(StringUtil.isNotBlank(map.get("content"))) {
                    vo.setContent((String) map.get("content"));
                }
                vo.setId((int)map.get("id"));

                vo.setCreateTime(map.get("createTime").toString());


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiniText() {
        return miniText;
    }

    public void setMiniText(String miniText) {
        this.miniText = miniText;
    }

    public String getImgUpload() {
        return imgUpload;
    }

    public void setImgUpload(String imgUpload) {
        this.imgUpload = imgUpload;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }
}
