package com.ep.controller.user.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ep.dao.model.user.User;
import com.ep.util.DateTimeUtility;

/**
 * Created by MengWeiBo on 2017-08-24
 */
public class UserVO {
    private Integer id;
    private String name;
    private String img;
    private String sex;
    private String mobile;
    private String remark;
    private String createDate;
    private String updateDate;
    private String updatedByName;

    public static List<UserVO> toVOs(List<User> users) {
        List<UserVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                UserVO vo = new UserVO();
                vo.setId(user.getId());
                vo.setName(user.getName());
                vo.setImg(user.getImg());
                vo.setSex(user.getSex());
                vo.setMobile(user.getMobile());
                vo.setRemark(user.getRemark());
                vo.setCreateDate(DateTimeUtility.formatYYYYMMDD(user.getCreateDate()));
                vo.setUpdateDate(DateTimeUtility.formatYYYYMMDD(user.getUpdateDate()));
                vo.setUpdatedByName(user.getUpdatedByName());

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }
}
