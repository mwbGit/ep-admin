package com.ep.dao.model.space;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ep.dao.model.user.User;

/**
 * Created by MengWeiBo on 2017-08-25
 */
public class UserSpace  implements Serializable {
    private static final long serialVersionUID = 1549226849768969923L;

    private Integer id;
    private Integer userId;
    private Integer spaceId;
    private User user;
    private Space space;

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

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
