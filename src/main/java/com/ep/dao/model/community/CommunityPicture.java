package com.ep.dao.model.community;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class CommunityPicture {
    private Integer id;
    private String url;
    private Integer communityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
