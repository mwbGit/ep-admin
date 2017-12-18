package com.ep.dao.model.community;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class CommunityUser {
    private Integer id;
    private Integer userId;
    private Integer communityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
