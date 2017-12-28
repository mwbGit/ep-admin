package com.ep.controller.wx.community.api;

import com.ep.dao.model.community.Community;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * -
 * Created by mengweibo on 2017/12/28.
 */
public class CommunityVO {
    private Integer id;
    private String name;
    private String tag;
    private List<String> pictures;

    public static List<CommunityVO> toVOs(List<Community> list) {
        List<CommunityVO> vos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)){
            for (Community community : list) {
                CommunityVO vo = new CommunityVO();
                vo.setId(community.getId());
                vo.setName(community.getName());
                vo.setTag(community.getTag());
                vo.setPictures(community.getPictures());
                vos.add(vo);
            }
        }

        return  vos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
