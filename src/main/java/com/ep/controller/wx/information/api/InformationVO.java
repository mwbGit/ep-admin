package com.ep.controller.wx.information.api;


import com.ep.util.DateTimeUtility;
import com.ep.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 吴晓海 on 2017/11/9.
 */
public class InformationVO {
    private Integer id;
    private String title;
    private String miniText;
    private String img;
    private String typeName;
    private String createTime;
    private String dateTime;
    private String content;
    private Integer clicksum;

    public static List<InformationVO> toVOs(List<Map<String, Object>> list) {
        List<InformationVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map map : list) {
                InformationVO vo = new InformationVO();
                vo.setId((int)map.get("id"));
                vo.setTitle((String)map.get("title"));
                vo.setMiniText((String)map.get("miniText"));
                vo.setImg((String)map.get("img"));
                vo.setTypeName((String)map.get("typeId"));
                vo.setContent((String)map.get("content"));
                vo.setClicksum((int)map.get("clicksum"));
                vo.setCreateTime(DateUtil.getTimeFormatText((Date)map.get("createTime")));
                vo.setDateTime(DateTimeUtility.formatYYYYMMDDHHMM((Date)map.get("createTime")));
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public Integer getClicksum() {
        return clicksum;
    }

    public void setClicksum(Integer clicksum) {
        this.clicksum = clicksum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}